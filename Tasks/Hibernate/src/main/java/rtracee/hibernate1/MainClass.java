package rtracee.hibernate1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        Session session = null;

        Scanner scanner = new Scanner(System.in);
        String startMessage = "Enter the command:\n/showProductsByPerson\n/findPersonsByProductTitle\n/removePerson(removeProduct)\n/buy\nor exit for finish:";

        System.out.println(startMessage);
        try {
            while (scanner.hasNext()){
                String command = scanner.nextLine();

                if (command.equals("exit"))
                    break;

                String[] commandPars = command.split(" ");

                switch (commandPars[0]){

                    case "buy":
                        buy(session, sessionFactory,commandPars);
                        break;

                    case "showProductsBuyPerson":
                        showProductsByPerson(commandPars, session,sessionFactory);
                        break;

                    case "findPersonByProductTitle":
                        findPersonByProductTitle(commandPars, session, sessionFactory);
                        break;

                    case "removePerson":
                        removePerson(commandPars, session, sessionFactory);
                        break;

                    default:
                        System.out.println("Error: wrong command");
                        break;
                }
                System.out.println(startMessage);
            }
        } finally {
            sessionFactory.close();
            session.close();
        }
    }

    public static void removeProduct(String[] command, Session session, SessionFactory sessionFactory){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Product product = (Product) session
                .createQuery("from Product p where p.name = :name")
                .setParameter("name", command[1]).getSingleResult();

        session.delete(product);
        System.out.println(product.getName() + " was deleted");
        session.getTransaction().commit();
    }

    public static void removePerson(String[] command, Session session, SessionFactory sessionFactory){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Person person = (Person) session
                .createQuery("from Persson p where p.name = :name")
                .setParameter("name", command[1]).getSingleResult();

        session.delete(person);
        System.out.println(person.getName() + " was deleted.");
        session.getTransaction().commit();
    }

    public static void findPersonByProductTitle(String[] command, Session session, SessionFactory sessionFactory){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Product product = (Product) session
                .createQuery("from Product p where p.name = :name")
                .setParameter("name", command[1]).getSingleResult();

        List<Order> orders = product.getOrders();
        System.out.println("Persons for " + product.getName() + ":");
        for(Order o : orders)
            System.out.println(o.getPerson());

        session.getTransaction().commit();
    }

    public static void buy(Session session, SessionFactory sessionFactory, String[] commandPars){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Person person = (Person) session
                .createQuery("from Person p where p.name = :name")
                .setParameter("name", commandPars[1]).getSingleResult();
        Product product = (Product) session
                .createQuery("from Product p where p.name = :name")
                .setParameter("name", commandPars[2]).getSingleResult();

        OrderKey orderKey = new OrderKey();
        orderKey.setPersonId(person.getId());
        orderKey.setProductId(product.getId());

        Order order = new Order();
        order.setOrderKey(orderKey);
        order.setPrice(product.getPrice());

        session.save(order);
        session.getTransaction().commit();
        System.out.println("Saved the next order: " + order.toString());
    }

    public static void showProductsByPerson(String[] command, Session session, SessionFactory sessionFactory){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Person person = (Person) session
                .createQuery("from Person p where p.name = :name")
                .setParameter("name", command[1]).getSingleResult();

        List<Order> orders = person.getOrders();
        for (Order o : orders)
            System.out.println("Products for " + person.getName() + ": \n"
            + o.getProduct().toString());

        session.getTransaction().commit();
    }
}

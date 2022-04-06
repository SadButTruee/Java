import java.util.*;

class PhoneBook {
    private Map<String, List<Integer>> map = new HashMap<>();

    public void add(String surname, int phoneNumber) {
        if (map.containsKey(surname)){
            map.get(surname).add(phoneNumber);
            return ;
        }
        List<Integer> list = new LinkedList<>();
        list.add(phoneNumber);
        map.put(surname, list);
    }

    public void get(String surname) {
        if (map.containsKey(surname)) {
            for (Integer i : map.get(surname))
                System.out.println(i);
        }
    }
}

public class Lesson {
    public static void main(String[] args) {
        String[] array = new String[]{"Moscow", "Moscow", "Samara", "Samara", "Vladikavkaz","Samara","Samara",
                "Kazan","Samara", "SPB","Samara", "Sochi"
};
        List<String> list = new ArrayList<>(Arrays.asList(array));
        Set<String> set = new HashSet<>(list);
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
        }

        System.out.println("*********************************************");

        PhoneBook telephoneDirectory = new PhoneBook();

        telephoneDirectory.add("Petrov", 1983647273);
        telephoneDirectory.add("Ivanov", 1038475983);
        telephoneDirectory.add("Sidorov", 1972349874);
        telephoneDirectory.add("Ivanov", 1874594875);
        telephoneDirectory.add("Korshunov", 1242452412);
        telephoneDirectory.add("Ivanov", 1234729834);
        telephoneDirectory.add("Sidorov", 123234234);

        telephoneDirectory.get("edrger");
        System.out.println("Petrov's number:");
        telephoneDirectory.get("Petrov");
        System.out.println("Ivanovs's number:");
        telephoneDirectory.get("Ivanov");
        System.out.println("Sidorovs's number:");
        telephoneDirectory.get("Sidorov");
        System.out.println("Korshunovs's number:");
        telephoneDirectory.get("Korshunov");
    }
}

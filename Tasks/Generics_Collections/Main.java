import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class  Fruit{
    private float weight = 0;
    private String name;

    public float getWeight() {return weight;}

    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    public void setWeight(float weight) {this.weight = weight;}
}

class Apple extends Fruit{
    public Apple(String name) {
        setWeight(1.0f);
        setName(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}

class Orange extends Fruit{
    public Orange(String name) {
        setWeight(1.5f);
        setName(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}

class Box<T extends Fruit>{
    private List<T> fruitBasket = new ArrayList<>();

    public List<T> getFruitBasket() {
        return fruitBasket;
    }

    public boolean compare(Box o) {
        return this.getWeight() == o.getWeight();
    }

    public void treplacement(Box<? super T> box){
        box.fruitBasket.addAll(this.fruitBasket);
        this.fruitBasket.clear();
    }

    public void  addFruit(T f){
        fruitBasket.add(f);
    }

    public double getWeight(){
        if (fruitBasket.size() == 0)
            return 0.0;
        return fruitBasket.size() * fruitBasket.get(0).getWeight();
    }

    @Override
    public String toString() {
        return "fruitBasket=" + fruitBasket + '}';
    }
}

public class Main {

    //public static void treplacementElementsOfArray(Object array, int index1, int index2){
    public static <T> void treplacementElementsOfArray(T[] array, int index1, int index2){
        T element = array[index1];
        array[index1] = array[index2];
        array[index2] = element;
    }

    public static <T>  ArrayList<T> arrayToArrayList(T[] array){
        //return new ArrayList<T>(Arrays.asList(array));
        ArrayList<T> strList = new ArrayList<>();
        Collections.addAll(strList, array);
        array = null;
        return  strList;
    }

    public static void main(String args[]) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 5; i++)
            appleBox.addFruit(new Apple("Apple" + (i + 1)));

        for (int i = 0; i < 5; i++)
            orangeBox.addFruit(new Orange("Orange" + (i + 6)));

        System.out.println(appleBox);
        System.out.println(orangeBox);

        //appleBox.addFruit(new Orange("A"));

        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());

        System.out.println(appleBox.compare(orangeBox));

        //appleBox.treplacement(orangeBox);

        Integer[] array = new Integer[]{1,2,3,4,5,6,7};
        for (Integer i : array)
            System.out.print(i + " ");
        System.out.println();
        treplacementElementsOfArray(array,0,6);
        for (Integer i : array)
            System.out.print(i + " ");
        System.out.println();


        List list = arrayToArrayList(array);
        System.out.println(list);
    }
}
import java.util.*;

//        1 Create an array with a set of words (10-20 words, there should be repeated ones).
//        Find and output a list of unique words that make up the array (we don't count duplicates).

//        2 Write a simple Phone class A directory that stores a list of surnames and phone numbers.
//        You can add entries to this phone directory using the add() method, and use the get() method
//        to search for a phone number by last name. It should be noted that there may be several phones
//        under one surname (in the case of namesakes), then when requesting such a surname, all phones should be displayed.

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

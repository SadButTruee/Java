package Lesson8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//1. Create an array with a set of words, and use the Stream API to find the most common;
//        2. Create an array of objects of the Employee type (with the fields Name, Age, Salary)
//           and calculate the average salary of the employee;
//        3. Write a method that can find the N most senior employees in the array of employees from item 2
//           and prints a message like “The N most senior employees are called: name1, name2, namen;” to the console.

class Employee{
    String name;
    int age;
    int salary;

    public Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }
}

public class StreamAPI {

    public static void main(String[] args) {
        //1)
//        String[] arr = new String[]{"A", "B", "C", "D", "A", "B", "C", "A", "B", "A"};
//        Map< String, Long > wordsByCount = Arrays.stream(arr)
//                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
//
//        String prevalentWord = wordsByCount.entrySet().stream()
//              .max(Map.Entry.comparingByValue()).get().getKey();
//        System.out.println(prevalentWord);

//        String[] arr = new String[]{"A", "B", "C", "D", "A", "B", "C", "A", "B", "A"};
//        String result =  Arrays.stream(arr)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream()
//                        .max(Comparator.comparing(a -> a.getValue())).get().getKey();
//        System.out.println(result);

        //1)
        String[] arr = new String[]{"A", "B", "C", "D", "A", "B", "C", "A", "B", "A"};
        String result =  Arrays.stream(arr)
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println(result);

        System.out.println("*************************************************");

        //2)
        Employee[] emp = new Employee[]{
                new Employee("emp1", 11, 100),
                new Employee("emp2", 22, 200),
                new Employee("emp3", 33, 300),
                new Employee("emp4", 44, 400),
                new Employee("emp5", 55, 500)};

        Double a = Arrays.stream(emp)
                .collect(Collectors.averagingInt(Employee::getSalary));
        System.out.println(a);
        System.out.println("*************************************************");

        olderEmploers(emp, 3);
    }
    //3)
    public static void olderEmploers(Employee[] emp ,int n){
        System.out.println(Arrays.stream(emp)
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(n)
                .map(Employee::getName)
                .collect(Collectors.joining(" , ","N самых старших сотрудников зовут: ", ".")));
    }

}

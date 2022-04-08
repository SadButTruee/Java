import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

//1. You need to write two methods that do the following: 1) Create a one-dimensional long array, for example:
//          static final int SIZE = 10 000 000;
//          static final int HALF = size / 2;
//          float[] arr = new float[size].
//        2) Fill this array with units.
//        3) They mark the execution time: long a = System.currentTimeMillis().
//        4) They go through the entire array and count a new value for each cell according to the formula:
//            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2))
//        5) The end time of the System.currentTimeMillis() method is checked. 6) The console displays the operating time: System.out.println(System.currentTimeMillis() - a).
//
//        The difference between the first method and the second:
//        ● The first one just runs through the array and calculates the values.
//        ● The second one splits the array into two arrays, calculates new values in two threads and
//        then glues these arrays back into one.
//        By time measurements:
//        For the first method, it is necessary to count the time only for the calculation cycle
//        For the second method, you measure the time of splitting the array into 2, calculating each of the two arrays and gluing.

public class Lesson {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE/2;

    public static void main(String[] args) {
        method();
        methodWithThreads();
    }

    public static void method(){
        float[] arr = new float[SIZE];
        Arrays.fill(arr,1);
        long before = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        System.out.println(System.currentTimeMillis() - before);
    }

    public static void methodWithThreads(){
        float[] arr = new float[SIZE];
        Arrays.fill(arr,1);
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        long before = System.currentTimeMillis();
        System.arraycopy(arr,0,a1,0,HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);
        Thread t1 = new Thread(() -> {

                for (int i = 0; i < a1.length; i++)
                    a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        });
        Thread t2 = new Thread(() -> {
                for (int i = 0,j = HALF; i < a2.length; i++, j++)
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1,0,arr,0,HALF);
        System.arraycopy(a2,0,arr,HALF,HALF);
        System.out.println(System.currentTimeMillis() - before);
    }
}

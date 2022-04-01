import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class MyArraySizeException extends Exception{
    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception{

    int i;
    public MyArrayDataException(String message, int sum, int row, int column, String[][] str) {
        super(message + ": row " + row + " column " + column + " - " + str[row][column]);
        this.i = sum;
    }
}

public class Main {
    public static void main(String[] args) {
        int i = 0;
        String[][] strArray = new String[][]{
                {"11", "12", "13", "14"},
                {"21", "22", "23", "24"},
                {"31", "32", "3d3", "34"},

//                {"String4.1", "String4.2", "String4.3", "String5.4"},
                {"41", "42", "43", "54"},};

        try {
            i = sumArray(strArray);
        } catch (MyArraySizeException e1) {
            e1.printStackTrace();
        }catch (MyArrayDataException e2){
            e2.printStackTrace();
            i = e2.i;
        }
        System.out.println(i);
    }

    public static int sumArray(String[][] str) throws MyArraySizeException, MyArrayDataException{
        String[][] array = new String[4][4];
        int sum = 0;
        if (array.length != str.length)
            throw new  MyArraySizeException("Неправильный размер массива");
        for (int i = 0; i < str.length; i++){
            for(int j = 0; j < str[i].length;j++){
                try {
                    sum += Integer.parseInt(str[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неправильное значение элемента массива",sum, i, j, str);
                }
            }
        }
        return sum;
    }
}

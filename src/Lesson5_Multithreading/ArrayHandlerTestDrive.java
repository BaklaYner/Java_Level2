package Lesson5_Multithreading;

import java.util.Arrays;

public class ArrayHandlerTestDrive {

    public static void main(String[] args) {
        ArrayHandler handler = new ArrayHandler();
        float[] a = handler.handleArray();
        float[] b = handler.handleArrayMultithreaded();
        System.out.println("Array \'a\' is equal to array \'b\': " + Arrays.equals(a, b));
    }

}
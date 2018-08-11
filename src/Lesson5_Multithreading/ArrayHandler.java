package Lesson5_Multithreading;

public class ArrayHandler {
    private final int SIZE = 10_000_000;
    private final int HALF_SIZE = SIZE / 2;
    private float[] arr = new float[SIZE];
    private float[] arr2 = new float[SIZE];

    public ArrayHandler() {
        fillArray();
    }

    private void fillArray() {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
            arr2[i] = 1;
        }
    }

    public float[] handleArray() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] *
                    Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        long time = System.currentTimeMillis() - a;

        System.out.printf("Time spent on the processing of array in one thread: %d milliseconds\n", time);
        return arr;
    }

    public float[] handleArrayMultithreaded() {
        float[] first = new float[HALF_SIZE];
        float[] second = new float[HALF_SIZE];

        long a = System.currentTimeMillis();
        System.arraycopy(arr2, 0, first, 0, HALF_SIZE);
        System.arraycopy(arr2, HALF_SIZE, second, 0, HALF_SIZE);

        Handler thread1 = new Handler(first, 0);
        Handler thread2 = new Handler(second, HALF_SIZE);
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(first, 0, arr2, 0, HALF_SIZE);
        System.arraycopy(second, 0, arr2, HALF_SIZE, HALF_SIZE);
        long time = System.currentTimeMillis() - a;

        System.out.printf("Time spent on the processing of array in two threads: %d milliseconds\n", time);
        return arr2;
    }

}
package Lesson5_Multithreading;

public class ArrayHandler {
    private final int SIZE = 10_000_000;
    private final int HALF_SIZE = SIZE / 2;
    private float[] arr = new float[SIZE];

    private void fillArray() {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
    }

    public float[] handleArray() {
        fillArray();
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long time = System.currentTimeMillis() - a;

        System.out.printf("Time spent on the processing of array in one thread: %d milliseconds\n", time);
        return arr;
    }

    public float[] handleArrayMultithreaded() {
        fillArray();
        float[] first = new float[HALF_SIZE];
        float[] second = new float[HALF_SIZE];

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, first, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, second, 0, HALF_SIZE);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF_SIZE; i++) {
                    first[i] = (float) (first[i] * Math.sin(0.2f + i / 5) *
                            Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF_SIZE; i++) {
                    second[i] = (float) (second[i] * Math.sin(0.2f + (i + HALF_SIZE) / 5) *
                            Math.cos(0.2f + (i + HALF_SIZE) / 5) * Math.cos(0.4f + (i + HALF_SIZE) / 2));
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(first, 0, arr, 0, HALF_SIZE);
        System.arraycopy(second, 0, arr, HALF_SIZE, HALF_SIZE);
        long time = System.currentTimeMillis() - a;

        System.out.printf("Time spent on the processing of array in two threads: %d milliseconds\n", time);
        return arr;
    }

}
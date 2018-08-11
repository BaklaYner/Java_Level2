package Lesson5_Multithreading;

class Handler extends Thread {
    private final float[] ARR;
    private final int OFFSET;

    Handler(float[] arr, int offset) {
        ARR = arr;
        OFFSET = offset;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < ARR.length; i++) {
            ARR[i] = (float) (ARR[i] *
                    Math.sin(0.2f + (i + OFFSET) / 5) *
                    Math.cos(0.2f + (i + OFFSET) / 5) *
                    Math.cos(0.4f + (i + OFFSET) / 2));
        }
    }

}
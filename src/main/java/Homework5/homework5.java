package Homework5;

public class homework5 {
    public class MainClass {
        private static final int Size = 10000000;
        private static final int HalfSize = Size / 2;

        public float[] calculate(float[] arr) {
            for (int i = 0; i < arr.length; i++)
                arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));
            return arr;
        }

        public void runOneThread() {
            float[] arr = new float[Size];
            for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
            long a = System.currentTimeMillis();
            calculate(arr);
            System.out.println("One thread method ends with: " + (System.currentTimeMillis() - a));
        }

        public void runTwoThreads() {
            float[] arr = new float[Size];
            float[] arr1 = new float[HalfSize];
            float[] arr2 = new float[HalfSize];
            for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;

            long a = System.currentTimeMillis();
            System.arraycopy(arr, 0, arr1, 0, HalfSize);
            System.arraycopy(arr, HalfSize, arr2, 0, HalfSize);

            new Thread() {
                public void run() {
                    float[] a1 = calculate(arr1);
                    System.arraycopy(a1, 0, arr1, 0, a1.length);
                }
            }.start();

            new Thread() {
                public void run() {
                    float[] a2 = calculate(arr2);
                    System.arraycopy(a2, 0, arr2, 0, a2.length);
                }
            }.start();

            System.arraycopy(arr1, 0, arr, 0, HalfSize);
            System.arraycopy(arr2, 0, arr, HalfSize, HalfSize);
            System.out.println("Two threads ends with: " + (System.currentTimeMillis() - a));
        }

        public static void main(String s[]) {
            MainClass o = new MainClass();
            o.runOneThread();
            o.runTwoThreads();
        }
    }
}

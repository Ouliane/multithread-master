import java.util.Arrays;

class MaxThread extends Thread {
    private int lo, hi;
    private int[] arr;
    private double max = 0;

    public MaxThread(int[] arr, int lo, int hi) {
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    public void run() {
        for (int i = lo; i < hi; i++) {
            double sin = Math.sin(arr[i]);
            if (max < sin)
                max = sin;
        }
    }

    public double getMax() {
        return max;
    }
}

public class MaxMultithreaded {
    /**
     * Find the max of the elements of an array.
     *
     * @param arr array to sum
     * @return max of the array's elements
     * @throws InterruptedException shouldn't happen
     */
    public static double max(int[] arr, int numThreads) throws InterruptedException {
        int len = arr.length;
        double max = 0;

        // Create and start threads.
        MaxThread[] ts = new MaxThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            ts[i] = new MaxThread(arr, (i * len) / numThreads, ((i + 1) * len / numThreads));
            ts[i].start();
        }

        // Wait for the threads to finish and get max value.
        for (int i = 0; i < numThreads; i++) {
            ts[i].join();
            if (max < ts[i].getMax())
                max = ts[i].getMax();
        }
        return max;

    }
}

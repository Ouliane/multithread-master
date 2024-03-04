import lombok.Getter;

import java.util.Random;

public class SharedCounter {
    static int counter = 0;


    public static void reset() {
        counter = 0;
    }

    public static int increment(int numThreads, int numIncrementsPerThread) throws InterruptedException {
        CounterThread[] counterThreadsArr = new CounterThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            counterThreadsArr[i] = new CounterThread(numIncrementsPerThread);
            counterThreadsArr[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            counterThreadsArr[i].join();
        }
        return counter;
    }
}


class CounterThread extends Thread {
    private int amount;

    public CounterThread(int amount) {
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < amount; i++) {
            SharedCounter.counter++;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

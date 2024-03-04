import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CountDownLatch;

public class ReverseHelloMultithreaded {
    public static void doReverseHello() throws InterruptedException {
        ReverseHelloThread startThread = new ReverseHelloThread(1);
        startThread.start();
        startThread.join();
    }
}

@Getter
class ReverseHelloThread extends Thread {
    private int threadNum;

    ReverseHelloThread(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        try {
            if (this.threadNum < 50) {
                int newThreadNum = this.threadNum + 1;
                ReverseHelloThread newThread = new ReverseHelloThread(newThreadNum);
                newThread.start();
                newThread.join();
            }
            System.out.println(String.format("Hello from thread %s", threadNum));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
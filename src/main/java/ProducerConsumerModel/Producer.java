package ProducerConsumerModel;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-09
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class Producer implements Runnable{

    private  BlockingDeque<PCData> blockingDeque;

    private volatile boolean isRunning = true;

    private AtomicInteger count = new AtomicInteger();

    private static final int SLEEPTIME = 10000;

    public Producer(BlockingDeque<PCData> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        PCData pcData = null;
        Random r = new Random();
        System.out.println("start producer id is: "+Thread.currentThread().getId());

        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                pcData = new PCData(count.incrementAndGet());
                System.out.println(pcData+"data is put in Queue");
                if (!blockingDeque.offer(pcData, 2, TimeUnit.SECONDS)) {
                    System.err.println("failed to put in 缓冲区 data"+pcData);
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop () {
        isRunning = false;
    }
}

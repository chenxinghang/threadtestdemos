package ProducerConsumerModel;

import com.sun.xml.internal.bind.v2.runtime.output.Pcdata;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-09
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class Consumer implements Runnable{

    private BlockingDeque<PCData> blockingDeque;

    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingDeque<PCData> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        System.out.println("start a consumer id: "+Thread.currentThread().getId());
        Random r = new Random();
        try {
            while (true) {
                PCData pcdata = blockingDeque.take();
                if (null != pcdata) {
                    int re = pcdata.getData() * pcdata.getData();
                    System.out.println(MessageFormat.format("{0}*{1} = {2}",pcdata.getData(),pcdata.getData(),re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}

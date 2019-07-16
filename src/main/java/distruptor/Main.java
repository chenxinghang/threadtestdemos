package distruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-15
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class Main {
    public static void main(String ... args) throws InterruptedException {

        ExecutorService es = Executors.newCachedThreadPool();
        PCDataFactory pcDataFactory = new PCDataFactory();
        int bufferSize = 1024;
        Disruptor<PCData> disruptor = new Disruptor<>(pcDataFactory,bufferSize, es, ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(new Consumer(),new Consumer(),new Consumer());
        disruptor.start();
        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        Productor productor = new Productor(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long i=0; true; i++) {
            bb.putLong(0,i);
            productor.pushData(bb);
            Thread.sleep(100);
            System.out.println("add data"+i);
        }

    }
}

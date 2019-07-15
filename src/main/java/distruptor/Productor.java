package distruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-15
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class Productor {

    private final RingBuffer<PCData> ringBuffer;

    public Productor(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData (ByteBuffer bb) {
        long sequence = this.ringBuffer.next();

        try {
            PCData pcData = ringBuffer.get(sequence);
            pcData.setValue(bb.getLong(0));
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}

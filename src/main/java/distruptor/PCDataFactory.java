package distruptor;

import com.lmax.disruptor.EventFactory;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-15
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class PCDataFactory implements EventFactory<PCData> {

    @Override
    public PCData newInstance() {
        return new PCData();
    }
}

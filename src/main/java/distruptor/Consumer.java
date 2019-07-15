package distruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-15
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class Consumer implements WorkHandler<PCData> {

    @Override
    public void onEvent(PCData pcData) throws Exception {

        System.out.println("current thread:"+Thread.currentThread().getId()+" Exent:--"+pcData.getValue()*pcData.getValue()+"--");
        
    }
}

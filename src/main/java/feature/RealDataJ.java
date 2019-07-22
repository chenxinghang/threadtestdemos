package feature;

import java.util.concurrent.Callable;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-22
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class RealDataJ implements Callable<String> {

    private String str;

    public RealDataJ(String str) {
        this.str = str;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<10; i++) {
            sb.append(str);
            try {
                Thread.sleep(100);
            }catch (Exception e) {

            }
        }
        return sb.toString();
    }
}

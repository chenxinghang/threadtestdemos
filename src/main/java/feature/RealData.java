package feature;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-22
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class RealData implements Data{

    protected final String realSult;

    public RealData(String realSult) {
        StringBuffer sb = new StringBuffer();
        sb.append(realSult);
        for (int i=0; i<10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            realSult = sb.toString();
        }
        this.realSult = realSult;
    }

    @Override
    public String getResult() {
        return realSult;
    }
}

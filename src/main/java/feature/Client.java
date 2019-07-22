package feature;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-22
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class Client {

    public Data request(final String queryStr) {
        final FeatureData featureData = new FeatureData();
        new Thread() {
            public void run(){
                RealData realData = new RealData(queryStr);
                featureData.setRealData(realData);
            }
        }.start();
        return featureData;
    }
}

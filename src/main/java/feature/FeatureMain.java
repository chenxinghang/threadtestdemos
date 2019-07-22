package feature;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-22
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class FeatureMain {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        FutureTask<String> featureTask = new FutureTask<>(new RealDataJ("a"));
        ExecutorService es = Executors.newFixedThreadPool(1);
        es.submit(featureTask);
        try {
            Thread.sleep(2000);
        }catch (Exception e) {

        }
        System.out.println("电脑数据是:"+featureTask.get());
    }
}

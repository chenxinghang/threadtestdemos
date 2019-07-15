import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-08
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class AutomicIntegerDemo {

    static AtomicInteger i = new AtomicInteger();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int k = 0; k < 10; k++) {
            threads[k] = new Thread(new AddThread());
        }
        for (int i = 0; i < 10; i++) threads[i].start();
        for (int i = 0; i < 10; i++) threads[i].join();
        System.out.println(i);
    }
}

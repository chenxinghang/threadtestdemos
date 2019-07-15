import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-08
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class AtomicIntegerArrayDemo {

    static AtomicIntegerArray array = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                array.getAndIncrement(i % array.length());
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) ts[k].start();
        for (int k = 0; k < 10; k++) ts[k].join();
        System.out.println(array);
    }
}

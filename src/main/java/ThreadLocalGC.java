import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-01
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class ThreadLocalGC {

    static volatile ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(this.toString() + " is gc");
        }
    };

    static volatile CountDownLatch cd = new CountDownLatch(10000);

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (tl.get() == null) {
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
                        @Override
                        protected void finalize() throws Throwable {
                            super.finalize();
                            System.out.println(this.toString() + " is gc");
                        }
                    });
                    System.out.println(Thread.currentThread().getId() + ":Create SimpleDateFormat");
                }
                Date t = tl.get().parse("2019-06-06 21:21:21" + i % 60);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cd.countDown();
            }
        }
    }

    public static void main(String... args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            es.execute(new ParseDate(i));
        }
        cd.await();
        System.out.println("mission complete!!");
        tl = null;
        System.gc();
        System.out.println("first GC complete!!");
        tl = new ThreadLocal<SimpleDateFormat>();

        for (int i = 0; i < 10000; i++) {
            es.execute(new ParseDate(i));
        }
        cd.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println("Second GC completed");
        es.shutdown();
    }
}

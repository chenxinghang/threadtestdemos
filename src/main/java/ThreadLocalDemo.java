import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: LEON
 * <p>
 * Date: 2019-06-28
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class ThreadLocalDemo {

    //sdf不是线程安全的，所以多线程使用会报错
    private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();

    public static class ParseDate implements Runnable {

        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {

            try {
                Date t = sdf.parse("2019-6-28 23:33:33");
                System.out.println(t + "date is " + t);
            } catch (Exception e3) {
                e3.printStackTrace();
            }

        }


    }

    public static class ParseDate2 implements Runnable {

        int i = 0;

        public ParseDate2(int i) {
            this.i = i;
        }

        @Override
        public void run() {

            try {
                if (tl.get() == null) {
                    tl.set(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
                    System.out.println(1111);
                }
                Date t = tl.get().parse("2019-6-28 23:33:33");
                System.out.println(t + "date is " + t);
            } catch (Exception e3) {
                e3.printStackTrace();
            }

        }
    }

    public static void main(String... args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            es.execute(new ParseDate2(11));
        }
        es.shutdown();
    }
}

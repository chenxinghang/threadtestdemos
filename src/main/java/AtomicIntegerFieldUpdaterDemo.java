import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-09
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class AtomicIntegerFieldUpdaterDemo {
    public static class Caldidate {
        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Caldidate> scoreUpdateer = AtomicIntegerFieldUpdater.newUpdater(Caldidate.class, "score");
    //检查upateer是否正确
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String... args) throws InterruptedException {
        final Caldidate stu = new Caldidate();
        Thread[] ts = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            ts[i] = new Thread() {
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdateer.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };
            ts[i].start();
        }
        for (int i = 0; i < 10000; i++) ts[i].join();

        System.out.println("updater 的分数是" + stu.score);
        System.out.println("原子类分数是" + allScore.get());


    }
}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * User: LEON
 * <p>
 * Date: 2019-06-14
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class FixedThreadPollDemo {


    public static void main(String... args) {
        /**
         * 插入入参数num
         * newFiexeThread 测速额是用一个LinkedBlockingQueue作为存放任务的队列，有序，最大值为int的最大值
         * 线程池子的num，最大值也为num 即：
         * 以为线程池的固定大小不变是num的值，所以，它的参数配置的corePoolSize和maxiumPoolSize的大小是一样的，
         * 因为使用的WorkQueue是LinkedBlockingQuuee，最大值是int的最大值（size很大），所以可以在任务非常频繁，系统处理缓慢的
         * 时候很容消耗掉系统资源。
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    System.out.println(System.currentTimeMillis() / 100 + " " + Thread.currentThread().getName());
                }
            });
        }

    }
}

import java.util.concurrent.*;

/**
 * User: LEON
 * <p>
 * Date: 2019-06-17
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class ThreadProlicyDemo {

    public static class MyTask implements Runnable {
        public void run() {
            System.out.println(System.currentTimeMillis()/1000+" ThreadName:"+Thread.currentThread());
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * fixdePool的默认策略是拒绝策略直接抛出异常
     * @param args
     * @throws InterruptedException
     */
    public static void main(String ... args) throws InterruptedException{
        MyTask task = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString()+"is discared");
            }
        });
        for (int i=0; i<Integer.MAX_VALUE; i++) {
            executorService.submit(task);
            Thread.sleep(10);
        }
    }
}

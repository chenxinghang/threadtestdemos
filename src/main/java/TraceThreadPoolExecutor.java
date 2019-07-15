import java.util.concurrent.*;

/**
 * User: LEON
 * <p>
 * Date: 2019-06-20
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task, clienTrace(), Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clienTrace(), Thread.currentThread().getName()));
    }

    private Exception clienTrace() {
        return new Exception("Client stack trace");
    }

    private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
        return new Runnable() {
            public void run() {
                try {
                    task.run();
                } catch (Exception e) {
                    clientStack.printStackTrace();
                }
            }
        };
    }

    public static void main(String... args) {
        ExecutorService pool = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.MICROSECONDS,
                new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
//            Future f = pool.submit(new DivTask(100,i));
//            try {
//                f.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
            pool.execute(new DivTask(100, i));
        }
        pool.shutdown();
    }

}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: LEON
 * <p>
 * Date: 2019-06-18
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class ExpandThreadDemo {

    public static class MyTask implements Runnable {
        public String name;

        public MyTask(String name) {
            this.name = name;
        }
        public void run() {
            System.out.println("正在执行 ThreadID:"+Thread.currentThread().getId()+" TaskName:"+name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String ... args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5,5,0L,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(5)) {
            @Override
            protected void beforeExecute(Thread thread,Runnable r) {
                System.out.println("read to run task name is ["+((MyTask)r).name+"]");
            }
            @Override
            protected void afterExecute(Runnable r,Throwable t) {
                System.out.println("after run tast name is ["+((MyTask)r).name+"]");
            }
            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }

        };
        for (int i=0; i<10; i++) {
            MyTask task = new MyTask("嘿嘿["+i+"]");
            es.execute(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}

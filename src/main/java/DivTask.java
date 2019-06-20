import java.util.concurrent.*;

/**
 * User: LEON
 * <p>
 * Date: 2019-06-20
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class DivTask implements Runnable{

    private int a;

    private int b;


    public DivTask() {
    }

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void run() {

        double c = a / b;
        System.out.println(c);
    }

    public static void main(String ... args) {
        ExecutorService es = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L, TimeUnit.MICROSECONDS,new SynchronousQueue<Runnable>());
        for (int i =0; i<5; i++) {
            /**
             * 这里使用submit必须呀要用的 Future接受不然会吃掉异常栈
             */
//           Future fe = es.submit(new DivTask(100,i));
//            try {
//                fe.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
            es.execute(new DivTask(100,i));
        }
        es.shutdown();
    }

}

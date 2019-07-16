/**
 * User: LEON
 * <p>
 * Date: 2019-07-15
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public final class FalseSharing implements Runnable{

    public final static int MAX_THREAD_SIZE = 2;
    public final static long INTERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;


    private static VolatileLong[] longs = new VolatileLong[MAX_THREAD_SIZE];

    static {
        for (int i = 0;i <longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    @Override
    public void run() {
        long i = INTERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public static void main(String ... args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        runtest();
        System.out.println("duration:"+(System.currentTimeMillis() - start));
    }

    private static void runtest() throws InterruptedException {
        Thread[] threads = new Thread[MAX_THREAD_SIZE];
        for (int i =0; i<threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    public static class VolatileLong {
        public volatile long value = 0L;
        public long p1, p2, p3, p4, p5, p6, p7; // 用来填充cacheLine
    }
}

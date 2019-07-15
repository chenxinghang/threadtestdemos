import org.omg.PortableInterceptor.INACTIVE;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-08
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);
//    static AtomicReference<Integer> money = new AtomicReference<>(19);

    public static void main(String[] args) {
        // 多个进程同时更新后台数据库，给用户充钱
        for (int i = 0; i < 3; i++) {
            final int timestamp = money.getStamp();
            new Thread() {
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.getReference();

//                            Integer m = money.get();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
//                                if (money.compareAndSet(m,m+20)) {
                                    System.out.println("余额小于20元，充值成功，余额:" + money.getReference() + "元");
//                                    System.out.println("余额小于20元，充值成功，余额:"+money.get()+"元");
                                    break;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }.start();
        }
        // 用户消费，模拟消费行为
        new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        int timestamp = money.getStamp();
                        Integer m = money.getReference();
//                        Integer m = money.get();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
//                            if (money.compareAndSet(m,m-10)) {
                                System.out.println("消费10元 余额：" + money.getReference() + "元");
//                                System.out.println("消费10元 余额："+money.get()+"元");
                                break;
                            } else {
                                System.out.println("没有足够的金额");
                                break;
                            }
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}

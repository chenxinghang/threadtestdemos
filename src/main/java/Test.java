
/**
 * User: LEON
 * <p>
 * Date: 2019-06-26
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class Test {

    public static void main1 (String ... args) {
       DivTask a = new DivTask();
       DivTask b = new DivTask();
       DivTask t = a,p=t;

        /**
         * 主要是为了验证哟没有可能不相等，发现是有可能的
         */

       System.out.println(t != (t = b));
       System.out.println(t);
       System.out.println(a);
       System.out.println(b);
    }

    public static void main (String ... args) {
        DivTask a = new DivTask();
        DivTask b = new DivTask();
        System.out.println(a);
        System.out.println(b);
        DivTask t = a,p=t;
        System.out.println( t == p);
        a = b;
        System.out.println(t == p);
    }
}
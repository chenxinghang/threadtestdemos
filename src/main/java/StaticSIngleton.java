/**
 * User: LEON
 * <p>
 * Date: 2019-07-09
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class StaticSIngleton {

    private StaticSIngleton() {
        System.out.println("StaticSIngleton is creating");
    }

    private static class SingleHolder{
        private static StaticSIngleton instance = new StaticSIngleton();

    }

    public static StaticSIngleton getInstance() {
        return SingleHolder.instance;
    }

}

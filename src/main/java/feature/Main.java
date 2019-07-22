package feature;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-22
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class Main {

    public static void main(String... args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");

        try {
            Thread.sleep(2000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(data.getResult());
    }
}

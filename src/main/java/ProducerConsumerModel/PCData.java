package ProducerConsumerModel;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-09
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class PCData {

    private final int data;

    public PCData(int data) {
        this.data = data;
    }

    public PCData(String data) {
        this.data = Integer.parseInt(data);
    }

    public int getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "data=" + data +
                '}';
    }
}

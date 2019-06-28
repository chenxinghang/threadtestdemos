import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * User: LEON
 * <p>
 * Date: 2019-06-28
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class SkipListDemo {

    public static void main(String ... args) {
        /**
         * 跳表是有序的
         */
        Map<Integer,Integer> map = new ConcurrentSkipListMap<>();
        Map<Integer,Integer> chMap = new ConcurrentHashMap<>();
        Map<Integer,Integer> hMap = new HashMap<>();
        for (int i=0; i<160; i++) {
            map.put(i,i);
            chMap.put(i,i);
            hMap.put(i,i);
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(String.format("key is %s, value is %s",entry.getKey(),entry.getValue()));
        }
        System.out.println("\n\n\n");
        for (Map.Entry entry : chMap.entrySet()) {
            System.out.println(String.format("key is %s, value is %s",entry.getKey(),entry.getValue()));
        }
        System.out.println("\n\n\n");
        for (Map.Entry entry : hMap.entrySet()) {
            System.out.println(String.format("key is %s, value is %s",entry.getKey(),entry.getValue()));
        }
    }
}

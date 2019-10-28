package paixu;

import java.util.Arrays;
import java.util.jar.JarEntry;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-26
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class MaoPaoSort extends PaiXu{

    public static void main(String ... args) {

        for (int i=0;i<J_arrays.length; i++) {
            for (int j=0;j< J_arrays.length; j++) {
                int temp = J_arrays[i];
                if (J_arrays[i]<J_arrays[j]){
                    J_arrays[i] = J_arrays[j];
                    J_arrays[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(J_arrays));
    }
}

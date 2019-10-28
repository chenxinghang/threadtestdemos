package paixu;

import java.util.Arrays;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-26
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class JOSort extends PaiXu{

    public static void main(String ... args) {
        int exchangeFlag=1,start=0;
        while (exchangeFlag ==1 || start == 1) {
            exchangeFlag = 0;
            for (int i= start;i<J_arrays.length-1;i+=2) {
                if (J_arrays[i]>J_arrays[i+1]) {
                    int temp = J_arrays[i];
                    J_arrays[i] = J_arrays[i+1];
                    J_arrays[i+1] = temp;
                        exchangeFlag = 1;
                }
            }
            if (start ==0) {
                start = 1;
            }else {
                start = 0;
            }
        }
        System.out.println(Arrays.toString(J_arrays));
    }
}

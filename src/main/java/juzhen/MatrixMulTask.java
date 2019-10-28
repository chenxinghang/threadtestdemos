package juzhen;


import com.itextpdf.text.pdf.parser.Matrix;

import java.util.concurrent.RecursiveTask;

/**
 * User: LEON
 * <p>
 * Date: 2019-08-01
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class MatrixMulTask extends RecursiveTask<Matrix> {

    Matrix m1;
    Matrix m2;
    String pos;

    public MatrixMulTask(Matrix m1, Matrix m2, String pos) {
        this.m1 = m1;
        this.m2 = m2;
        this.pos = pos;
    }

    @Override
    protected Matrix compute() {
        System.out.println(Thread.currentThread().getId()+"");
        return null;
    }


}

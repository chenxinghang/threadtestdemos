import sun.security.krb5.internal.crypto.Des;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * User: LEON
 * <p>
 * Date: 2019-07-09
 * <p>
 * Sign: My name is leon 兴趣使然写代码的人 ≡(▔﹏▔)≡ ≧ω≦
 **/
public class LockFreeVector<E> {

    private final AtomicReferenceArray<AtomicReferenceArray<E>> buckets;
    private final Descriptor<E> descriptor;

    private static final int N_BUCKET = 30;
    private static final int FIREST_BUCKET_SIZE = 8;

    public LockFreeVector() {
        this.buckets = new AtomicReferenceArray<>(N_BUCKET);
        buckets.set(0, new AtomicReferenceArray<E>(FIREST_BUCKET_SIZE));
        this.descriptor = new Descriptor<>(0, null);
    }

    static class Descriptor<E> {

        public int size;
        volatile WirterDescriptor wirteop;

        public Descriptor(int size, WirterDescriptor wirteop) {
            this.size = size;
            this.wirteop = wirteop;
        }

        public void completeWrite() {
            WirterDescriptor<E> tmpOp = wirteop;
            if (tmpOp != null) {
                tmpOp.doIt();
                wirteop = null;
            }
        }

        public Descriptor get() {
            return this;
        }
    }

    static class WirterDescriptor<E> {
        public E oldV;
        public E newV;
        public AtomicReferenceArray addr;
        public int addr_ind;

        public WirterDescriptor(E oldV, E newV, AtomicReferenceArray addr, int addr_ind) {
            this.oldV = oldV;
            this.newV = newV;
            this.addr = addr;
            this.addr_ind = addr_ind;
        }

        public void doIt() {
            addr.compareAndSet(addr_ind, oldV, newV);
        }
    }

    /**
     * 拖过size的前置0的偏移量计算在位置
     */

//    public void push_back(E e) {
//        Descriptor<E> desc;
//        Descriptor<E> newd;
//        do {
//            desc = descriptor.get();
//            desc.completeWrite();
//            int pos = desc.size + FIREST_BUCKET_SIZE;
//            int zeroNumberPos = Integer.numberOfLeadingZeros(pos);
//            int bucketInd = zronumberFirest - zeroNumberPos;
//            if (buckets.get(bucketInd))
//
//        }while ()
//    }
}

package com.jett.jvm;

/**
 * @author jett
 */
//2.这里给出一个gc输出，要求给出一个你认为最可能的启动JVM参数，并说明为什么？
//Heap
//def new generation   total 6464K, used 115K [0x34e80000, 0x35580000, 0x35580000)
//eden space 5760K,   2% used [0x34e80000, 0x34e9cd38, 0x35420000)
//from space 704K,   0% used [0x354d0000, 0x354d0000, 0x35580000)
//to   space 704K,   0% used [0x35420000, 0x35420000, 0x354d0000)
//tenured generation   total 18124K, used 8277K [0x35580000, 0x36733000, 0x37680000)
//the space 18124K,  45% used [0x35580000, 0x35d95758, 0x35d95800, 0x36733000)
//compacting perm gen  total 16384K, used 16383K [0x37680000, 0x38680000, 0x38680000)
//the space 16384K,  99% used [0x37680000, 0x3867ffc0, 0x38680000, 0x38680000)
//ro space 10240K,  44% used [0x38680000, 0x38af73f0, 0x38af7400, 0x39080000)
//rw space 12288K,  52% used [0x39080000, 0x396cdd28, 0x396cde00, 0x39c80000)

// 第2题
//1. 根据提供的边界值 ，计算新生代 Xmn  7m
//2. 根据新生代和老年代的边界值 计算Xmx 40m
//3. 根据perm区边界，计算MaxPermSize    16m
//4. 无法给出正确的Xms和Permsize，只能计算出它们的范围（此项不作要求）
public class JvmHeapCalc {
    public static void main(String[] args) {
        System.out.println("new generation = Xmn：" + (0x35580000 - 0x34e80000) / 1024.0 / 1024);
        System.out.println("max(tenured generation) - min(new generation) = Xmx：" + (0x37680000 - 0x34e80000) / 1024.0 / 1024);
        System.out.println("perm gen = MaxPermSize：" + (0x38680000 - 0x37680000) / 1024.0 / 1024);
        
    }
}

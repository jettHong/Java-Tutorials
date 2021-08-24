package com.jett.java.concurrent.cas;

/**
 * CAS（Compare-and-Swap），即比较并替换。
 * CAS需要有3个操作数：内存地址V，旧的预期值A，即将要更新的目标值B。
 * @set sun.misc.Unsafe#compareAndSwapInt
 *
 * CAS的缺点：
 * 循环时间长开销很大：我们可以看到getAndAddInt方法执行时，如果CAS失败，会一直进行尝试。如果CAS长时间一直不成功，可能会给CPU带来很大的开销。
 * 只能保证一个共享变量的原子操作：当对一个共享变量执行操作时，我们可以使用循环CAS的方式来保证原子操作，但是对多个共享变量操作时，循环CAS就无法保证操作的原子性，这个时候就可以用锁来保证原子性。
 * Q：ABA问题：什么是ABA，ABA问题怎么解决？
 * -- 解决CAS机制中ABA问题的AtomicStampedReference详解 https://my.oschina.net/u/3194531/blog/4489065
 *
 * @author jett
 */
public class CASTest {

}

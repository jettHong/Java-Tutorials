package com.jett.java8.optional;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Optional 的使用
 *
 * @author jett
 *
 * REF：
 * 理解、学习与使用 Java 中的 Optional
 * https://www.oschina.net/translate/understanding-accepting-and-leveraging-optional-in
 */
public class OptionalTest {
    
    /**
     * 一、创建 Optional  实例
     * Optional.of(obj)
     * obj 为 null 时，抛出 NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void whenCreateOfNullOptional_then_NullPointerException() {
        Optional<Integer> of = Optional.of(null);
        System.out.println("上面报错了, 本行没办法打印出来.");
    }
    
    /**
     * Optional.ofNullable(obj)
     * obj 可以为 null。
     */
    @Test
    public void whenCreateOfNullable() {
        // ofNullable 可接受一个 null 值.
        Optional<Integer> ofNullable = Optional.ofNullable(null);
        
        // isPresent() => 如果存在值则返回true ，否则返回false 。
        boolean present = ofNullable.isPresent();
        Assert.assertEquals(present, false);
    }
    
    /**
     * 二、访问 Optional 对象的值
     * Optional.get()
     * 如果是 empty，抛出 NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_then_NoSuchElementException() {
        Optional<Integer> emptyOpt = Optional.empty();
        emptyOpt.get();
    }
    
    /**
     * 三、判断是否有值
     * isPresent() => 如果存在值则返回true ，否则返回false 。
     * ifPresent => 如果有值则执行传入的 Lambda 表达式.
     */
    @Test
    public void present() {
        Optional<Integer> opt = Optional.ofNullable(Integer.valueOf(123));
        
        boolean present = opt.isPresent();
        Assert.assertEquals(present, true);
        
        opt.ifPresent(i -> {
            System.out.println(i);
        });
    }
    
    /**
     * 四、条件取得默认值
     * orElse =>
     * orElseGet =>  短路判断
     */
    @Test
    public void orElse() {
        {
            // 当t1 == null
            // orElse\orElseGet 都会执行.
            Integer t1 = null;
            Integer t2 = Integer.valueOf(456);
            Integer result1 = Optional.ofNullable(t1).orElse(createNewInteger(456));
            Assert.assertEquals(result1, t2);
            Integer result2 = Optional.ofNullable(t1).orElseGet(() -> createNewInteger(456));
            Assert.assertEquals(result2, t2);
        }
        System.out.println("-----------------------------");
        {
            // 当t1 != null
            // -------------- 重要！！！-----------------
            // 两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。
            // 不过，orElse() 方法仍然创建了对象。
            // 与之相反，orElseGet() 方法不创建对象。
            // 在执行较密集的调用时，比如调用 Web 服务或数据查询，这个差异会对性能产生重大影响。
            Integer t1 = Integer.valueOf(123);
            Integer t2 = Integer.valueOf(456);
            Integer result1 = Optional.ofNullable(t1).orElse(createNewInteger(456));
            Assert.assertEquals(result1, t1);
            Integer result2 = Optional.ofNullable(t1).orElseGet(() -> createNewInteger(456));
            Assert.assertEquals(result2, t1);
        }
    }
    
    Integer createNewInteger(int i) {
        System.out.println("createNewInteger");
        return new Integer(i);
    }
    
    /**
     * 五、返回异常
     * Optional.get()
     * 如果是 empty，抛出 IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenThrowException() {
        Optional<Object> emptyOpt = Optional.empty();
        emptyOpt.orElseThrow(() -> new IllegalArgumentException("orElseThrow 这个方法让我们有更丰富的语义，可以决定抛出什么样的异常，而不总是抛出 NullPointerException"));
    }
    
    /**
     * 六、转换值
     * map()
     * flatMap() => 用于返回字段类型也是Optional的
     */
    @Test
    public void map() {
        Optional<User> opt = Optional.of(new User("张三", null));
        
        // .map
        Optional<String> nameOpt = opt.map(i -> i.name);
        String name = nameOpt.orElse("无名");
        Assert.assertEquals("张三", name);
        
        // .flatMap => 用于返回字段类型也是Optional的
        Optional<String> addrOpt = opt.flatMap(i -> i.addr);
        String addr = addrOpt.orElse("默认地址");
        
    }
    
    class User {
        public String name;
        public Optional<String> addr;
        
        public User(String name, Optional<String> addr) {
            this.name = name;
            this.addr = addr;
        }
    }
    
    /**
     * 七、过滤
     * filter()
     */
    @Test
    public void filter() {
        Optional<User> opt = Optional.of(new User("张三", null));
        Assert.assertEquals(opt.filter(i -> "张三".equals(i.name)).isPresent(), true);
        Assert.assertEquals(opt.filter(i -> "李四".equals(i.name)).isPresent(), false);
    }
    
    
}

package com.jett.java8.stream;

import com.google.gson.Gson;
import com.jett.SampleBean;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream
 *
 * @author jett
 * <p>
 * REF：
 * https://www.cnblogs.com/JavaWeiBianCheng/tag/stream/
 * https://zhuanlan.zhihu.com/p/28112239
 * https://mp.weixin.qq.com/s/zgv8MCw5D2bR1rU84loN6A (20 个实例玩转 Java 8 Stream)
 */
public class StreamTest {
    
    /**
     * 示例数据
     */
    public static List<String> strings = Arrays.asList("111", null, "", "333", "222", "444", "", "555", "333");
    
    static List<Integer> intList = Arrays.asList(6, 3, 2, 9, 4, 6, 7, -1);
    
    static List<SampleBean> beanList = Arrays.asList(
            SampleBean.Builder.create().name("小东").age(10).sex("男").friends(Arrays.asList("小明", "华强", "狗蛋")).build(),
            SampleBean.Builder.create().name("小花").age(18).sex("女").friends(Arrays.asList("小东")).build(),
            SampleBean.Builder.create().name("华强").age(18).sex("男").friends(Arrays.asList("小东")).build(),
            SampleBean.Builder.create().name("狗蛋").age(40).sex("男").build()
    );
    
    /**
     * 循环
     */
    @Test
    public void forEach() {
        strings.forEach(System.out::print);
        strings.stream().forEach(System.out::print);
        strings.parallelStream().forEach(System.out::print);
        // Stream 和 parallelStream 区别
        // 并行流 parallelStream 提供了流的并行处理，它是Stream的另一重要特性，其底层使用Fork/Join框架实现。
        // 简单理解就是多线程异步任务的一种实现。
        
        System.out.println();
        System.out.println(beanList);
        beanList.stream().forEach(i->i.setAge(123));
        System.out.println(beanList);
        
    }
    
    /**
     * 排序
     */
    @Test
    public void sorted() {
        System.out.println(intList);
        // 排序数值
        Stream<Integer> sortedList = intList.stream().sorted(Comparator.comparingInt(m -> m.intValue()));
        System.out.println(Arrays.toString(sortedList.toArray()));

        // 排序字串
        Collections.sort(strings, (x, y) -> {
            if (x == null) {
                return -1;
            } else if (y == null) {
                return 1;
            } else {
                return x.compareTo(y);
            }
        });
        System.out.println(strings);
        //FIXME 本例中有null元素，直接.sorted会导致 NPE。

//        System.out.println(collect);
        // 下面示例所说可以避免NPE。
        // Comparator.nullsFirst()、Comparator.nullsLast()
        // list.stream().sorted(Comparator.comparing(l -> l.getCreateTime(), Comparator.nullsFirst(Date::compareTo))).collect(Collectors.toList());
//        List<SampleBean> sortedBeanList = beanList.stream().sorted(
//                Comparator.comparing(i -> i.getFriends(), Comparator.nullsFirst(List::size::compare))
//                ).collect(Collectors.toList());
    }
    
    /**
     * 过滤
     */
    @Test
    public void filter() {
        List<String> filtered = strings.stream().filter(str -> (str != null && !str.isEmpty())).collect(Collectors.toList());
        System.out.println(filtered);
    }
    
    /**
     * 聚合操作：count、min、max、average、sum
     */
    @Test
    public void statistics() {
        // 计数 方法1
        long total1 = strings.stream().count();
        // 计数 方法2
        long total2 = strings.stream().collect(Collectors.counting());
        Assert.assertEquals(total2, 9L);
        // 过滤后再计数
        long match = strings.stream().filter(i -> "333".equals(i)).count();
        Assert.assertEquals(match, 2L);
        
        // 最大值
        int maxAge = beanList.stream().map(SampleBean::getAge).collect(Collectors.maxBy(Integer::compare)).get();
        Assert.assertEquals(maxAge, 40);
        // 最小值
        int minAge = beanList.stream().map(SampleBean::getAge).collect(Collectors.minBy(Integer::compare)).get();
        Assert.assertEquals(minAge, 10);
        // 求和
        int sum = beanList.stream().collect(Collectors.summingInt(SampleBean::getAge));
        Assert.assertEquals(sum, 86);
        // 平均数
        Double averageAge = beanList.stream().collect(Collectors.averagingDouble(SampleBean::getAge));
        // 21.5
        System.out.println(averageAge);
        
        // 带上以上所有方法
        DoubleSummaryStatistics statistics = beanList.stream().collect(Collectors.summarizingDouble(SampleBean::getAge));
        System.out.println("count:" + statistics.getCount());
        System.out.println("max:" + statistics.getMax());
        System.out.println("max:" + statistics.getMin());
        System.out.println("sum:" + statistics.getSum());
        System.out.println("average:" + statistics.getAverage());
    }
    
    /**
     * 字符串分隔符连接
     */
    @Test
    public void joining() {
        String names = beanList.stream().map(SampleBean::getName).collect(Collectors.joining(",", "(", ")"));
        // (小东,小明,华强,狗蛋)
        System.out.println(names);
    
    
        Object[] objects = beanList.stream().map(SampleBean::getName).toArray();
        // (小东,小明,华强,狗蛋)
        System.out.println(objects);
    }
    
    /**
     * 分组
     */
    @Test
    public void groupingBy() {
        // 按年龄分组
        // (m -> m.getAge()) 等同于 (SampleBean::getAge)
        Map<Integer, List<SampleBean>> collect = beanList.stream().collect(Collectors.groupingBy(SampleBean::getAge));
        for (Map.Entry<Integer, List<SampleBean>> integerListEntry : collect.entrySet()) {
            System.out.println("key=" + integerListEntry.getKey());
            System.out.println("value=" + integerListEntry.getValue());
            System.out.println("----");
        }
        System.out.println(collect);
    
        System.out.println("-------------多重分组,先根据[性别]再根据[年龄]分-------------------");
        Map<String, Map<Integer, List<SampleBean>>> multipleMap = beanList.stream().collect(Collectors.groupingBy(SampleBean::getSex, Collectors.groupingBy(SampleBean::getAge)));
        System.out.println(new Gson().toJson(multipleMap));
    
//        System.out.println("-------------根据[性别]分组合计[年龄]-------------------");
//        Map<String, List<SampleBean>> sexs = beanList.stream().collect(Collectors.groupingBy(SampleBean::getSex));
//        System.out.println(new Gson().toJson(sexs));
//        sexs.values().stream().collect(Collectors.summingDouble(i->{
//            i.stream().collect()
//        }));
    
    }
    
    /**
     * 分区
     */
    @Test
    public void partitioningBy() {
        //分成两部分，一部分大于10岁，一部分小于等于10岁
        Map<Boolean, List<SampleBean>> partMap = beanList.stream().collect(Collectors.partitioningBy(i -> i.getAge() > 18));
        System.out.println(partMap);
    }
    
    /**
     * TODO 规约
     */
    @Test
    public void reducing() {
        //规约
        Integer allAge = beanList.stream().map(SampleBean::getAge).collect(Collectors.reducing(Integer::sum)).get();
        System.out.println(allAge);
    }
    
    /**
     * 匹配
     */
    @Test
    public void match() {
        // allMatch 是否全匹配，此例子输出 false (因为其中有一个 -1)
        System.out.println(intList.stream().allMatch(i -> i > 0));
        
        // noneMatch 反全匹配，此例子输出 false (因为其中有一个 -1)
        System.out.println(intList.stream().noneMatch(i -> i > 0));
        
        // anyMatch 任一匹配，此例子输出 true (大部分大于 0)
        System.out.println(intList.stream().anyMatch(i -> i > 0));
    }
    
    /**
     * 去重
     */
    @Test
    public void distinct() {
        // 简易版本去重
        intList.stream().distinct().forEach(System.out::println);
        
        // 自定义去重，通过 filter
        System.out.println(beanList.stream().filter(distinctByKey(SampleBean::getAge)).collect(Collectors.toList()));
    }
    
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>(16);
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    
    /**
     * 转换
     */
    @Test
    public void collect() {
    
        //TODO beanList.stream().peek()
        
        // 转成list
        List<Integer> ageList = beanList.stream().map(SampleBean::getAge).collect(Collectors.toList());
        System.out.println(ageList);
        
        // 转成set
        Set<Integer> ageSet = beanList.stream().map(SampleBean::getAge).collect(Collectors.toSet());
        System.out.println(ageSet);
        
        // 转成map,注:key不能相同，否则报错
        Map<String, Integer> nameAgeMap = beanList.stream().collect(Collectors.toMap(SampleBean::getName, SampleBean::getAge));
        System.out.println(nameAgeMap);
        // name: Student
        Map<String, SampleBean> collect = beanList.stream().collect(Collectors.toMap(SampleBean::getName, item -> item));
        System.out.println(collect);
    
    
        //static Map<String, SampleBean> map = beanList.stream().collect(Collectors.toMap(SampleBean::getName, SampleBean::getAge));
    }
    
    /**
     * 转换
     */
    @Test
    public void toSet() {
        String str = "1,2,3,4,1";
        Set<String> collect = Arrays.stream(str.split(",")).collect(Collectors.toSet());
        System.out.println(collect);
    }
    
    //TODO 将Map转换成List
    //TODO 将List转换成Map
    //TODO FlatMap 方法使用 https://www.cnblogs.com/JavaWeiBianCheng/p/11936136.html
    
    public static void main(String[] args) {
        // Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了4个随机数
        Random random = new Random();
        random.ints().limit(4).forEach(System.out::println);
        
    }
    
}

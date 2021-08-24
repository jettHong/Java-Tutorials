方法引用是java8的新特性之一， 可以直接引用已有Java类或对象的方法或构造器。
方法引用与[lambda表达式](https://www.jianshu.com/p/8d7f98116693)结合使用，可以进一步简化代码。

java8方法引用有四种形式：

- 静态方法引用　　　　　　　：　 　ClassName :: staticMethodName
- 构造器引用　　　　　　　　：　 　ClassName :: new
- 类的任意对象的实例方法引用：　 　ClassName :: instanceMethodName
- 特定对象的实例方法引用　　：　 　object :: instanceMethodName

**静态方法引用**的语法格式为： 类名::静态方法名 ，
如 System.out::println 等价于 lambda表达式 s -> System.out.println(s) ，代码示例：

```java
public class Java8Tester {
    public static void main(String[] args) {
        List<String> names = new ArrayList();
        
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        
        // 方法引用使用一对冒号 ::
        names.forEach(System.out::println);
    }
}
```

静态方法引用适用于lambda表达式主体中仅仅调用了某个类的静态方法的情形。



**构造器引用**的语法格式为： **类名::new** ，如() -> new ArrayList<String>() 等价于 ArrayList<String>::new，代码示例：

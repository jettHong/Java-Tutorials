# 接口

JDK1.8 开始，接口允许定义默认方法和静态方法。



### **1、默认方法(default-methods)**

##### **为什么要有默认方法？**

在 java 8 之前，接口与其实现类之间的 **耦合度** 太高了（**tightly coupled**），当需要为一个接口添加方法时，所有的实现类都必须随之修改。默认方法解决了这个问题，它可以为接口添加新的方法，而不会破坏已有的接口的实现。这在 lambda 表达式作为 java 8 语言的重要特性而出现之际，为升级旧接口且保持向后兼容（backward compatibility）提供了途径。

Default methods enable you to add new functionality to the interfaces of your libraries and ensure binary compatibility with code written for older versions of those interfaces.

##### **语法**

在接口中的**实现方法**签名前加上 `default` 关键字。

```java
default 修饰关键字 methodName (参数列表) {
	// 实现体 
}
```

##### **示例**

```java
interface InterfaceA {
    default void foo() {
        System.out.println("InterfaceA foo");
    }
}

class ClassA implements InterfaceA {
}

public class Test {
    public static void main(String[] args) {
        new ClassA().foo(); // 打印：“InterfaceA foo”
    }
}
```

##### **默认方法可以被继承**

默认方法同样可以被继承

```java
interface InterfaceA {
    default void foo() {
        System.out.println("Interface A foo");
    }
}

interface InterfaceB extends InterfaceA {
    // 接口B 继承于 接口A 但没有自己的实现。
}

interface InterfaceC extends InterfaceA {
    // 接口C 继承于 接口A 重写自己的实现。
    @Override
    default void foo() {
        System.out.println("Interface C foo");
    }
}

interface InterfaceD extends InterfaceA {
    // 覆写默认方法并将它重新声明为抽象方法，这样新接口的子类必须再次覆写并实现这个抽象方法。
    @Override
    void foo();
}


public class Test {
    public static void main(String[] args) {
        
        new InterfaceB() {
        }.foo(); // 打印：“Interface A foo”
        
        new InterfaceC() {
        }.foo(); // 打印：“Interface C foo”
        
        new InterfaceD() {
            @Override
            public void foo() {
                System.out.println("Interface D foo");
            }
        }.foo(); // 打印：“InterfaceD foo”
        
        // 或者使用 lambda 表达式
        ((InterfaceD) () -> System.out.println("Interface D foo - lambda")).foo(); // 打印：“Interface D foo - lambda”
        
    }
}
```

- 不覆写默认方法，直接从父接口中获取方法的默认实现。
- 覆写默认方法，这跟类与类之间的覆写规则相类似。
- 覆写默认方法并将它重新声明为抽象方法，这样新接口的子类必须再次覆写并实现这个抽象方法。

##### **默认方法的多继承**

Java 使用的是单继承、多实现的机制，为的是避免多继承带来的调用歧义的问题。当接口的子类同时拥有具有相同签名的方法时，就需要考虑一种解决冲突的方案。

```java
interface InterfaceA {
    default void foo() {
        System.out.println("InterfaceA foo");
    }
}
 
interface InterfaceB {
    default void bar() {
        System.out.println("InterfaceB bar");
    }
}
 
interface InterfaceC {
    default void foo() {
        System.out.println("InterfaceC foo");
    }
    
    default void bar() {
        System.out.println("InterfaceC bar");
    }
}

/**
 * 在 ClassA 类中，它实现的 InterfaceA 接口和 InterfaceB 接口中的方法不存在歧义，可以直接多实现。
 */
class ClassA implements InterfaceA, InterfaceB {
}

/**
 * 错误！！！
 * 在 ClassB 类中，它实现的 InterfaceB 接口和 InterfaceC 接口中都存在相同签名的 foo 默认方法，需要手动解决冲突。
 */
//class ClassB implements InterfaceB, InterfaceC {
//}

/**
 * 覆写存在歧义的方法，并可以使用 InterfaceName.super.methodName(); 的方式手动调用需要的接口默认方法。
 */
class ClassB implements InterfaceB, InterfaceC {
    @Override
    public void bar() {
        InterfaceB.super.bar(); // 调用 InterfaceB 的 bar 方法
        InterfaceC.super.bar(); // 调用 InterfaceC 的 bar 方法
        System.out.println("ClassB bar"); // 做其他的事
    }
}
```

实现类实现不同接口的相同方法签名，需要覆写存在歧义的方法，并可以使用`InterfaceName.super.methodName();` 的方式手动调用需要的接口默认方法。

##### 接口继承行为发生冲突时的解决规则

子接口B 继承父接口A，子接口自动包含了父接口的方法，这时一个实现类同时 `implements` 父子接口，等同于只实现子接口，那么这个实现类在实现的接口方法内不能使用 父接口.super.xxx();

```java
interface InterfaceA {
    default void foo() {
        System.out.println("InterfaceA foo");
    }
}

interface InterfaceB extends InterfaceA {
    @Override
    default void foo() {
        System.out.println("InterfaceB foo");
    }
}

// 正确
class ClassA implements InterfaceA, InterfaceB {
}

class ClassB implements InterfaceA, InterfaceB {
    @Override
    public void foo() {
//        InterfaceA.super.foo(); // 错误
        InterfaceB.super.foo();
    }
}
```

如果要使用父接口的默认方法，可通过再创建接口C（跳板作用），在接口C中调用父接口的默认方法，实现类同时 `implements` BC接口。

##### 接口与抽象类

当接口继承行为发生冲突时的另一个规则是，**类的方法声明优先于接口默认方法，无论该方法是具体的还是抽象的**。

### 2、静态方法(static-methods)

接口中的静态方法和类中定义的静态方法一样，不属于特定对象，所以它们不是实现接口的api的一部分，必须使用InterfaceName.staticMethod来调用它们。

#### 为什么接口要支持静态方法

**接口中的静态方法背后的思想是提供一种简单的机制，允许通过将相关的方法内聚在接口中，而不必创建新的对象。**

抽象类也可以做同样的事情。主要的区别在于抽象类可以有构造函数、成员变量和方法。

推荐把和只和接口相关的静态 utility 方法放在接口中（提高内聚性），而不需要额外创建一些 utility 类专门去放置这些方法。

### 注意要点

- `default` 关键字只能在接口中使用（以及用在 `switch` 语句的 `default` 分支），不能用在抽象类中。
- 接口默认方法不能覆写 `Object` 类的 `equals`、`hashCode` 和 `toString` 方法。
- 接口中的静态方法必须是 `public` 的，`public` 修饰符可以省略，`static` 修饰符不能省略。

ref：
https://www.cnblogs.com/wuhenzhidu/p/10753328.html

https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
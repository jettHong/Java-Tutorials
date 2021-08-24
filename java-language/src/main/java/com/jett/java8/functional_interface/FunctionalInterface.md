# 函数式接口(Functional Interface)

函数式接口(Functional Interface)就是一个**有且仅有一个抽象方法**（可以有多个非抽象方法）的接口。

函数式接口可以被隐式转换为 Lambda 表达式。

是为了支持 Lambda 表达式 所定义的接口。

# 如何声明为函数式接口
使用 `@FunctionalInterface` 注解能够强制要求接口为 ”函数式接口“

常见函数式接口：`java.lang.Runnable` 

java 中内置常用函数接口的目录：java.util.function









ref：https://www.runoob.com/java/java8-functional-interfaces.html
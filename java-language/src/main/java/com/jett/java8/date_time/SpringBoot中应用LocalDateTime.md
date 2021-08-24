SpringBoot 中应用 `LocalDateTime`  

将 `LocalDateTime` 字段以时间戳的方式返回给前端 添加日期转化类
```java
public class LocalDateTimeConverter extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeNumber(value.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}
```
并在 `LocalDateTime` 字段上添加 `@JsonSerialize(using = LocalDateTimeConverter.class)` 注解，如下：  
```java
@JsonSerialize(using = LocalDateTimeConverter.class)
protected LocalDateTime gmtModified;
```
将 `LocalDateTime` 字段以指定格式化日期的方式返回给前端  
在 `LocalDateTime` 字段上添加 `@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")` 注解即可，如下：  

```java
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
protected LocalDateTime gmtModified;
```

对前端传入的日期进行格式化 在 `LocalDateTime` 字段上添加 `@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")` 注解即可，如下：
```java
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
protected LocalDateTime gmtModified;
```


ref：https://mp.weixin.qq.com/s/Dd_7yUh3lq3TqE2cjsYXvw  
package com.jett.java.lang.reflection;

/**
 * POJO对象
 */
public class UserInfo extends UserInfoB {
    
    private String name;
    private int age;
    private String sex;
    
    private UserInfo(String name, int age) {
        System.out.println("构造方法（私有的）");
        this.age = age;
    }
    
    protected UserInfo(int age) {
        System.out.println("构造方法（受保护的）：" + age);
        this.age = age;
    }
    
    public UserInfo() {
    }
    
    public UserInfo(String name) {
        System.out.println("构造方法（一个参数-姓名）：" + name);
        this.name = name;
    }
    
    public UserInfo(String name, int age, String sex) {
        System.out.println("构造方法（多个参数）：" + name + age + sex);
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    
    private void whisper() {
        System.out.println("私有方法，无返回");
    }
    
    public String echo(String str) {
        System.out.println("公开方法，返回字串");
        return "echo=>" + str;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}

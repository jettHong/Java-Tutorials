package com.jett;

import lombok.Data;

import java.util.List;

/**
 * 演示Bean
 * @author jett
 */
@Data
//@Builder // 这里也可以使用 lombok 的建造器，但是只要添加这个注解，Bean类的构造器就无法使用。
public class SampleBean {
    
    private String name;
    private Integer age;
    private String sex;
    private List<String> friends;
    
    @Override
    public String toString() {
        return "SampleBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", friends=" + friends +
                '}';
    }
    
    public static final class Builder {
        private String name;
        private Integer age;
        private String sex;
        private List<String> friends;
        
        private Builder() {
        }
        
        public static Builder create() {
            return new Builder();
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder age(Integer age) {
            this.age = age;
            return this;
        }
        
        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }
        
        public Builder friends(List<String> friends) {
            this.friends = friends;
            return this;
        }
        
        public SampleBean build() {
            SampleBean sampleBean = new SampleBean();
            sampleBean.setName(name);
            sampleBean.setAge(age);
            sampleBean.setSex(sex);
            sampleBean.setFriends(friends);
            return sampleBean;
        }
    }
}

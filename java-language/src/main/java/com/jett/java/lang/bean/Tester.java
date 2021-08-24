package com.jett.java.lang.bean;

import java.util.List;

public class Tester {
    
    public static void main(String[] args) throws Exception {
        {
            DemoBean beanBefore = new DemoBean(null, "字串", 12.3D, 12345678L);
            DemoBean beanAfter = new DemoBean("", "字串", 12.0D, 0L);
            
            List<Comparison> compare = BeanCompare.compare(beanBefore, beanAfter);
            for (Comparison comparison : compare) {
                System.out.println(String.format("%s：%s->%s", comparison.getField(), comparison.getBefore(), comparison.getAfter()));
            }
            System.out.println("------------");
        }
    }
    
}

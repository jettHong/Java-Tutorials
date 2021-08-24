package com.jett.java8.lambda.test00;

import lombok.Builder;
import lombok.Data;

/**
 * Bean对象
 */
@Data
@Builder
class Project {
    /**
     * 项目名称
     */
    private String name;
    
    /**
     * 编程语言
     */
    private String language;
    
    /**
     * star 数
     */
    private Integer stars;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 作者
     */
    private String author;
}
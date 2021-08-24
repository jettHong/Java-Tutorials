package com.jett.java8.lambda.test00;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 为什么使用Lambda表达式
 */
public class LambdaTester00 {
    
    public static void main(String[] args) {
        List<Project> projectList = new ArrayList<>();
        projectList.add(Project.builder().name("springboot").language("java").stars(500).build());
        projectList.add(Project.builder().name("mybatis").language("java").stars(100).build());
        projectList.add(Project.builder().name("vue").language("javaScript").stars(200).build());
        
        // 一、使用旧方法做过滤
        // 1、创建过滤方法过滤
        LambdaTester00.filterJavaProjects(projectList);
        // 2、更进一步使用参数传递过滤条件
        LambdaTester00.filterProjectsByLanguage(projectList, "java");
        // 3、如果需求变更成要按点赞数过滤、描述等，那么问题来了，需要创建多少个过滤器？
        //  有点类似JPA查询？
        LambdaTester00.filterProjectsByLanguageAndStars(projectList, "java", 20);
        
        // 二、旧方法优化-使用策略模式
        // 比起用参数传递更好应对需求变化的方法，答案是行为参数化。
        // 对选择标准进行建模-抽象
        LambdaTester00.filterProjects(projectList, new LanguagePredicate("java"));
        LambdaTester00.filterProjects(projectList, new StarsPredicate(20));
        // 再进一步，使用匿名实现类
        LambdaTester00.filterProjects(projectList, new ProjectPredicate() {
            @Override
            public boolean test(Project project) {
                return "java".equals(project.getLanguage());
            }
        });
        
        // 三、Lambda表达式出现，替代匿名实现类
        LambdaTester00.filterProjects(projectList, i -> "java".equals(i.getLanguage()));
        
    }
    
    /**
     * 过滤方法，固定写死
     *
     * @param projects
     * @return
     */
    public static List<Project> filterJavaProjects(List<Project> projects) {
        List<Project> result = new ArrayList<>();
        for (Project project : projects) {
            if ("java".equals(project.getLanguage())) {
                result.add(project);
            }
        }
        return result;
    }
    
    /**
     * 过滤方法，通过传值判断
     *
     * @param projects
     * @return
     */
    public static List<Project> filterProjectsByLanguage(List<Project> projects, String language) {
        List<Project> result = new ArrayList<>();
        for (Project project : projects) {
            if (language.equals(project.getLanguage())) {
                result.add(project);
            }
        }
        return result;
    }
    
    /**
     * 过滤方法，多重判断
     *
     * @param projects
     * @return
     */
    public static List<Project> filterProjectsByLanguageAndStars(List<Project> projects, String language, Integer stars) {
        List<Project> result = new ArrayList<>();
        for (Project project : projects) {
            if (language.equals(project.getLanguage()) && project.getStars() > stars) {
                result.add(project);
            }
        }
        return result;
    }
    
    /**
     * 按照断言条件过滤
     *
     * @param projects
     * @param projectPredicate
     * @return
     */
    public static List<Project> filterProjects(List<Project> projects, ProjectPredicate projectPredicate) {
        List<Project> result = new ArrayList<>();
        for (Project project : projects) {
            if (projectPredicate.test(project)) {
                result.add(project);
            }
        }
        return result;
    }
    
    
}

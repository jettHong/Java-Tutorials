package com.jett.java8.lambda.test00;

/**
 * 编程语言过滤器实现
 */
public class LanguagePredicate implements ProjectPredicate {
    
    private final String language;
    
    public LanguagePredicate(String language) {
        this.language = language;
    }
    
    @Override
    public boolean test(Project project) {
        return language.equals(project.getLanguage());
    }
}

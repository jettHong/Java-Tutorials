package com.jett.java8.lambda.test00;

/**
 * 点赞过滤器实现
 */
class StarsPredicate implements ProjectPredicate {
    
    private final Integer starts;
    
    public StarsPredicate(Integer stars) {
        this.starts = stars;
    }
    
    @Override
    public boolean test(Project project) {
        return project.getStars() > starts;
    }
}

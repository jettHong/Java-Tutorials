package com.jett.java8.lambda;

import java.util.function.Function;

/**
 * Java将方法作为参数传递
 * https://www.jianshu.com/p/3514e0e96a9a
 */
public class LambdaResponseTest {
    public static class Response {
        private String html;
        
        public Response(String html) {
            this.html = html;
        }
        
        public String getHtml() {
            return html;
        }
    }
    
    public static class Request {
        private Function<Response, Object> callback;
        
        public Request(String name, Function<Response, Object> callbackFunction) {
            this.callback = callbackFunction;
        }
        
        public Function<Response, Object> getCallback() {
            return callback;
        }
    }
    
    public static class Engine {
        private Request request;
        
        public void setRequest(Request request) {
            this.request = request;
        }
        
        public void run() {
            Object o = request.getCallback().apply(new Response("I like moving, moving"));
            System.out.println(o);
        }
    }
    
    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.setRequest(new Request("1", xxx -> returnHtml(xxx)));
        engine.run();
        engine.setRequest(new Request("2", response -> returnHtmlb(response)));
        engine.run();
        engine.setRequest(new Request("3", response -> returnHtmlc(response)));
        engine.run();
    }
    
    public static Object returnHtml(Response response) {
        return response.getHtml();
    }
    
    public static Object returnHtmlb(Response response) {
        return response.getHtml() + " bb";
    }
    
    public static Object returnHtmlc(Response response) {
        return response.getHtml() + " ccc";
    }
    
    public static Object defDoNothing(Response response) {
        return null;
    }
}

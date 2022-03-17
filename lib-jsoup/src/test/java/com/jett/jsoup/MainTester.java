package com.jett.jsoup;

import cn.hutool.core.io.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainTester {
    
    /**
     * S01-解析成文档
     */
    @Test
    public void parsing() {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        
        System.out.println(doc);
        // 输出如下：
/*
<html>
 <head>
  <title>First parse</title>
 </head>
 <body>
  <p>Parsed HTML into a doc.</p>
 </body>
</html>
*/
    }
    
    /**
     * S02-解析部分TAG，形成完整的HTML文档
     */
    @Test
    public void parseBodyFragment() {
        String html = "<div><p>Lorem ipsum.</p>";
        Document doc = Jsoup.parse(html);
        
        System.out.println(doc);
        // 输出如下：
/*
<html>
 <head></head>
 <body>
  <div>
   <p>Lorem ipsum.</p>
  </div>
 </body>
</html>
*/
    }
    
    /**
     * S03-从WEB中获取并解析
     */
    @Test
    public void parseFromURL() throws IOException {
        Document doc = Jsoup.connect("https://jsoup.org/cookbook/input/load-document-from-url").get();
        System.out.println(doc);
    }
    
    /**
     * S04-从文件中获取并解析
     */
    @Test
    public void parseFroFile() throws IOException, URISyntaxException {
        URL resource = this.getClass().getClassLoader().getResource("main.html");
        URI uri = resource.toURI();
        System.out.println(uri);
        File uriFile = new File(uri.getPath());
        System.out.println(uriFile.getAbsoluteFile());
        Document doc = Jsoup.parse(uriFile, "UTF8");
        System.out.println(doc);
    }
    
    /**
     * 查找元素
     * dom navigation
     */
    @Test
    public void domNavigation() throws IOException, URISyntaxException {
        String htmlStr = FileUtil.readString("main.html", "UTF8");
        Document doc = Jsoup.parse(htmlStr);
        
        // 通过 ID 查询唯一
        Element elementById = doc.getElementById("list");
        System.out.println(elementById);
        
        // 通过 tag 查找
        Elements p = doc.getElementsByTag("p");
        p.forEach(System.out::println);
        
        // 通过 class 查找
        Elements boxCons = doc.getElementsByClass("box_con");
        boxCons.forEach(System.out::println);
        
        // 包含有某属性
        Elements cusAttribute = doc.getElementsByAttribute("cusAttribute");
        cusAttribute.forEach(System.out::println);
        // etc
    }
    
    /**
     * 查找元素
     * selector-syntax
     * REF:
     *  https://jsoup.org/apidocs/org/jsoup/select/Selector.html
     */
    @Test
    public void selectorSyntax() throws IOException, URISyntaxException {
        String htmlStr = FileUtil.readString("main.html", "UTF8");
        Document doc = Jsoup.parse(htmlStr);
        
        Elements elements = doc.select("body > div > div td");
        for (Element e : elements) {
            System.out.println(e);
        }
        // 上面不会打印出  <td>异常TD</td>
    
        Elements links = doc.select("a[href*=456]");
        for (Element e : links) {
            System.out.println(e);
        }
        // 上面只会打印：<a href="/123/456.html">新新新</a>
    
        Elements p = doc.select("td:has").select("p"); //TODO 是否包含子tag
        for (Element e : p) {
            System.out.println(e);
        }
        // <td><p>异常TD</p>></td>
    }
    
    /**
     * 元素数据
     */
    @Test
    public void elementData() throws IOException, URISyntaxException {
        String htmlStr = FileUtil.readString("main.html", "UTF8");
        Document doc = Jsoup.parse(htmlStr);
        // 取得第一个元素 .first()
        Element cusAttribute = doc.getElementsByAttribute("cusAttribute").first();
        System.out.println(cusAttribute.text());
        System.out.println(cusAttribute.id());
        System.out.println(cusAttribute.html());
        System.out.println(cusAttribute.outerHtml());
        System.out.println(cusAttribute.attr("cusAttribute"));
        System.out.println(cusAttribute.data());
    }
    
    /**
     * URL 属性
     * 绝对 URI、相对 URI
     */
    @Test
    public void workingWithUrls() throws IOException, URISyntaxException {
        Document doc = Jsoup.connect("http://jsoup.org").get();
        
        Element link = doc.select("a").first();
        String relHref = link.attr("href");     // "/"
        String absHref = link.attr("abs:href"); // "http://jsoup.org/"
    }
}

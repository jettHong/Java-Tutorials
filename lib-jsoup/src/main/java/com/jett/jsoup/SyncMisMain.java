package com.jett.jsoup;

import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.Data;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncMisMain {
    public static void main(String[] args) throws IOException {
        String url = "http://XXXXXX";
        String cookieStr = "SHAREJSESSIONID=请登陆后再复制过来";
        
        String body = Jsoup
                .connect(url)
                .cookies(converCookie(cookieStr))
                .ignoreContentType(true)
                .execute().body();
        
        AddressBook addressBook = JSONUtil.toBean(body, AddressBook.class);
        ExcelUtil.getWriter(new File("通讯录.xls")).write(addressBook.getRows()).flush();
        ;
        System.out.println(addressBook);
    }
    
    public static Map<String, String> converCookie(String cookie) {
        Map<String, String> map = new HashMap<>();
        Arrays.stream(cookie.split(";")).forEach(i -> {
            String[] split = i.split("=");
            String k = split[0];
            String v = split.length > 1 ? split[1] : "";
            map.put(k, v);
        });
        return map;
    }
    
    @Data
    class Item {
        String dept_name; // : "组织机构"
        String duty; // : null
        String email; // : ""
        String id; // : "001"
        String mobile; // : "13333333333"
        String rank; // : null
        String status; // : "在职"
        String supervisor_name; // : null
        String tel; // : null
        String username; // : "超级管理员"
        String usernum; // : null
    }
    
    @Data
    class AddressBook {
        List<Object> footer;
        List<Item> rows;
        Integer total;
        
    }
    
}

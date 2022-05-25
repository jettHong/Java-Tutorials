package com.jett.jsoup;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.setting.dialect.Props;
import lombok.Data;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncMisMain {
    public static void main(String[] args) throws IOException {
        Props props = new Props("config.properties");
        String url = props.getStr("url");
        String cookieStr = props.getStr("cookieStr");
    
        String body = Jsoup
                .connect(url)
                .cookies(converCookie(cookieStr))
                .ignoreContentType(true)
                .execute().body();
        
        AddressBook addressBook = JSONUtil.toBean(body, AddressBook.class);
        ExcelUtil.getWriter(new File("通讯录.xls")).write(addressBook.getRows()).flush();
        System.out.println(addressBook);
        
        String vcfTemp = "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "N:{surName};{givenName};;;\n" +
                "FN:{givenName} {surName}\n" +
                "ORG:{org};\n" +
                "TEL;TYPE=CELL;TYPE=pref;TYPE=VOICE:{mobile}\n" +
                "EMAIL;TYPE=WORK;TYPE=pref;TYPE=INTERNET:{email}\n" +
                "PRODID:-//Apple Inc.//iCloud Web Address Book 2205B32//EN\n" +
                "REV:2022-03-26T15:58:17Z\n" +
                "END:VCARD\n";
        
        StringBuffer sb = new StringBuffer(10240);
        addressBook.getRows().stream().forEach(i -> {
            Map<String, String> map = new HashMap<>();
            map.put("surName", StrUtil.sub(i.getUsername(), 0, 1));
            map.put("givenName", StrUtil.sub(i.getUsername(), 1, i.getUsername().length()));
            map.put("mobile", i.getMobile());
            map.put("org", "XXX公司");
            map.put("email", i.email);
            sb.append(StrUtil.format(vcfTemp, map));
        });
        FileUtil.writeString(sb.toString(), new File("通讯录.vcf"), StandardCharsets.UTF_8);
        
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
        String duty; // 职位
        String email; // 邮箱
        String id; // : 数据ID
        String mobile; // 手机号
        String rank; // : null
        String status; // : "在职"
        String supervisor_name; // : null
        String tel; // null
        String username; // 姓名
        String usernum; // 员工编号
    }
    
    @Data
    class AddressBook {
        List<Object> footer;
        List<Item> rows;
        Integer total;
        
    }
    
}

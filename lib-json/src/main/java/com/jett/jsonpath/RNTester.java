package com.jett.jsonpath;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RNTester {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = FileUtil.readString("RN.json", "UTF8");
        JSONObject json = JSON.parseObject(str);
        System.out.println(json);
        String encode = URLEncoder.encode(str, "UTF-8");
        System.out.println(encode);
    }
}

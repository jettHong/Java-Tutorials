package com.jett.jsonpath;

import cn.hutool.core.io.FileUtil;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    String json = FileUtil.readString("a.json", "UTF8");
        List<String> titless = JsonPath.read(json, "$..[?(@.ctrlType=='dic')]");
        System.out.println("ctrlType" + titless);
    }
}

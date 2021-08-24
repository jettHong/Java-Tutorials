package com.jett.algorithm.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class Q171_Excel_sheet_column_number {
    public static void main(String[] args) {
        Q171_Excel_sheet_column_number test = new Q171_Excel_sheet_column_number();
        
        // System.out.println('A' + 0);
        for (int i = 0; i < 26; i++) {
            // 'A' = 65 = 1
            // 'B' = 66 = 2
            cache.put(Character.valueOf((char) (65 + i)), i + 1);
        }
        for (Map.Entry<Character, Integer> entry : cache.entrySet()) {
//            System.out.println(entry);
        }
    
        System.out.println(test.titleToNumber("A"));  // 1
        System.out.println(test.titleToNumber("Z"));  // 26
        System.out.println(test.titleToNumber("AA")); // 27
        System.out.println(test.titleToNumber("BA")); // 53
        System.out.println(test.titleToNumber("ZZ")); // 702
        System.out.println(test.titleToNumber("EM")); // 143
        System.out.println(test.titleToNumber("ADT")); // 800
    }
    
    int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            /**
             * 个位直接取26的索引,即26进制的数字
             * 十位取26进制的数字后要 乘以26 (类似10位的数字"1"表示10,"2"表示 20,即 数字*10的一次方)
             * 百位取26进制的数字后要乘以26 的二次方(类似百位的数字"1"表示100,"2"表示200,即数字*10的二次方)
             * 所以:  26进制的数字为 n*26* 的(n的位数-1 次方)
             */
            char c = chars[i];
            Integer index = cache.get(c);
            int pow = chars.length - i - 1;
            result += index * Math.pow(26, Double.valueOf(pow));
        }
        return result;
    }
    
//
//    public String Int2Sn(int n) {
//        String s = "";
//
//        if (n == 0) {
//            s = "0";
//        }
//
//        //商==0 代表循环完毕
//        // 小余26的数字 除以26 等于0
//        while (n != 0) {
//            //对26取余
//            int i = n % 26;
//            //获取26进制数值
//            char c = MySerials[i];
//            //反向加入字符串中
//            s = c + s;
//            //求商
//            n = n / 26;
//        }
//        //返回结果
//        return s;
//    }
    
    static LinkedHashMap<Character, Integer> cache = new LinkedHashMap(26) {{
    }};
    
    
}

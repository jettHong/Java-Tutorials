package com.jett.java.text;

import java.text.DecimalFormat;

/**
 * Java数字格式化
 * 符号	说明
 * 0	显示数字，如果位数不够则补 0
 * #	显示数字，如果位数不够不发生变化
 * .	小数分隔符
 * -	减号
 * ,	组分隔符
 * E	分隔科学记数法中的尾数和小数
 * %	前缀或后缀，乘以 100 后作为百分比显示
 * ?	乘以 1000 后作为千进制货币符显示。用货币符号代替。如果双写，用国际货币符号代替；如果出现在一个模式中，用货币十进制分隔符代替十进制分隔符
 *
 * @author jett
 */
public class DecimalFormatTest {
    public static void main(String[] args) {
        // 实例化DecimalFormat类的对象，并指定格式
        DecimalFormat df1 = new DecimalFormat("0.0");
        DecimalFormat df2 = new DecimalFormat("#.#");
        DecimalFormat df3 = new DecimalFormat("000.000");
        DecimalFormat df4 = new DecimalFormat("###.###");
        float f = 1234.56F;
        // 对输入的数字应用格式，并输出结果
        System.out.println("0.0 格式：" + df1.format(f)); // 1234.6
        System.out.println("#.# 格式：" + df2.format(f)); // 1234.6
        System.out.println("000.000 格式：" + df3.format(f)); // 1234.560
        System.out.println("###.### 格式：" + df4.format(f)); // 1234.56
    }
}

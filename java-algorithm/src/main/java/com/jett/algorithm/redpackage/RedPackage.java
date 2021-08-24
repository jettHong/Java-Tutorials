package com.jett.algorithm.redpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 抢红包算法
 * 1、二倍均值法
 * 2、线段切割法
 *
 * @author jett
 */
public class RedPackage {
    
    
    /**
     * 生成min到max范围的浮点数
     */
    public static double nextDouble(final double min, final double max) {
        return min + ((max - min) * new Random().nextDouble());
    }
    
    /**
     * 生成min到max范围的浮点数
     */
    public static int nextInt(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }
    
    public static String format(double value) {
        return new java.text.DecimalFormat("0.00").format(value); // 保留两位小数
    }
    
    
    /**
     * 二倍均值法
     *
     * @param money  金额
     * @param number 数量
     * @return
     */
    public static List<Double> doubleMeanMethod(double money, int number) {
        List<Double> result = new ArrayList<Double>();
        if (money < 0.1 || number < 1) {
            return null;
        }
        double amount, sum = 0;
        int remainingNumber = number;
        int i = 1;
        while (remainingNumber > 1) {
            amount = nextDouble(0.01, 2 * (money / remainingNumber));
            sum += amount;
            System.out.println("第" + i + "个人领取的红包金额为：" + format(amount));
            money -= amount;
            remainingNumber--;
            result.add(amount);
            i++;
        }
        result.add(money);
        System.out.println("第" + i + "个人领取的红包金额为：" + format(money));
        sum += money;
        System.out.println("验证发出的红包总金额为：" + format(sum));
        
        return result;
        
    }
    
    
    /**
     * 二倍均值法
     * 思路：
     * 金额 = 随机区间[1分, m / n X 2)
     *
     * @param totalAmount       剩余总金额(单位为分，避免精度丢失)
     * @param totalPeopleNumber 总人数(单位为分，避免精度丢失)
     * @return
     */
    public static List<Integer> doubleRate(Integer totalAmount, Integer totalPeopleNumber) {
        Random random = new Random();
        Integer restAmount = totalAmount;
        Integer restPeopleNumber = totalPeopleNumber;
        Integer current = 1;
        List<Integer> result = new ArrayList<Integer>(totalPeopleNumber);
        for (int i = 0; i < totalPeopleNumber - 1; i++) {
            int max = restAmount / restPeopleNumber * 2;
            // 区间值：[1, max)
            current = random.nextInt(max - 1) + 1;
            result.add(current);
            restAmount -= current;
            restPeopleNumber--;
        }
        // 最后一个兜底
        result.add(restAmount);
        return result;
    }
    
    
    /**
     * 线段切割法
     *
     * @param money
     * @param number
     */
    public static void lineSegmentCutting(double money, int number) {
        if (money < 0 && number < 1) {
            System.out.println("输入错误！");
        }
        double begin = 0, end = money;
        double y = 0;
        for (int i = 0; i < number - 1; i++) {
            double nn = 0;
            double amount = nextDouble(begin, end);
            
            nn = amount - begin;
            System.out.println("第" + (i + 1) + "个人领取的红包金额为：" + format(nn));
            y += nn;
            begin = amount;
            
        }
        System.out.println("第" + number + "个人领取的红包金额为：" + format(end - begin));
        y += (end - begin);
        System.out.println("验证发出的红包总金额为：" + format(y));
        
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random);
        
        if (false) {
            Scanner sc = new Scanner(System.in);
            System.out.println("这是一段模拟抢红包的代码。");
            
            int number;
            double money;
            System.out.print("请输入红包总金额：");
            money = sc.nextDouble();
            System.out.print("请输入红包数量：");
            number = sc.nextInt();
            //System.out.println(money + " " + number);
            
            //二倍均值法
            List<Double> doubles = doubleMeanMethod(money, number);
            //System.out.println(doubleMeanMethod(money,number).toString());
            //也是可以直接输出list的，为了观察方便，我就在循环中输出了，存在list里主要是为了后续方便数据的使用
            System.out.println();
            
            //线段切割法
            lineSegmentCutting(money, number);
        }
        List<Integer> doubleRateList = doubleRate(100, 10);
        System.out.println(doubleRateList);
        System.out.println(doubleRateList.stream().collect(Collectors.summingInt(i -> i.intValue())));
        
    }
    
    
}

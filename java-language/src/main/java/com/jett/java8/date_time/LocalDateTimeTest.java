package com.jett.java8.date_time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author jett
 * REF：
 * https://mp.weixin.qq.com/s/Dd_7yUh3lq3TqE2cjsYXvw
 */
public class LocalDateTimeTest {
    
    
    public static void main(String[] args) {
        // =================== 年月日 LocalDate =================
        {
            //获取当前年月日
            LocalDate localDate = LocalDate.now();
            //构造指定的年月日
            LocalDate cusLocalDate = LocalDate.of(2019, 9, 10);
            
            // 取得年度
            int year0 = cusLocalDate.getYear();
            int year1 = cusLocalDate.get(ChronoField.YEAR);
            assert year0 == year1;
            
            // 取得月份
            Month month0 = cusLocalDate.getMonth();
            int month1 = cusLocalDate.get(ChronoField.MONTH_OF_YEAR);
            assert month0.getValue() == month1;
            
            // 取得日期
            int day0 = cusLocalDate.getDayOfMonth();
            int day1 = cusLocalDate.get(ChronoField.DAY_OF_MONTH);
            assert day0 == day1;
            
            // 取得周
            DayOfWeek dayOfWeek0 = cusLocalDate.getDayOfWeek();
            int dayOfWeek1 = cusLocalDate.get(ChronoField.DAY_OF_WEEK);
            assert dayOfWeek0.getValue() == dayOfWeek1;
        }
        
        // =================== 时分秒 LocalTime =================
        {
            LocalTime localTime = LocalTime.now();
            LocalTime cusLocalTime = LocalTime.of(13, 51, 10);
            //获取小时
            int hour = cusLocalTime.getHour();
            int hour1 = cusLocalTime.get(ChronoField.HOUR_OF_DAY);
            //获取分
            int minute = cusLocalTime.getMinute();
            int minute1 = cusLocalTime.get(ChronoField.MINUTE_OF_HOUR);
            //获取秒
            int second = cusLocalTime.getSecond();
            int second1 = cusLocalTime.get(ChronoField.SECOND_OF_MINUTE);
        }
        
        // =================== 年月日时分秒 LocalDateTime =================
        {
            LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
            LocalDateTime nowLocalDateTime = LocalDateTime.now();
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            // 由 localDate、localTime 变量构建
            LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
            LocalDateTime localDateTime3 = localDate.atTime(localTime);
            LocalDateTime localDateTime4 = localTime.atDate(localDate);
            
            // 取得LocalDate、LocalTime
            nowLocalDateTime.toLocalDate();
            nowLocalDateTime.toLocalTime();
        }
        
        // =================== 瞬时 Instant =================
        {
            // 获取秒数
            Instant instant = Instant.now();
            // 获取秒数
            long currentSecond = instant.getEpochSecond();
            // 获取毫秒数
            long currentMilli = instant.toEpochMilli();
            // 获取毫秒数（推荐）
            System.currentTimeMillis();
        }
        
        // =================== 时间计算-增减 =================
        {
            // 注意：LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，修改这些对象对象会返回一个副本
            // 增减天数
            LocalDateTime localDateTime = LocalDateTime.of(
                    2019, Month.SEPTEMBER, 10,
                    14, 46, 56);
            //增加一年
            localDateTime = localDateTime.plusYears(1);
            localDateTime = localDateTime.plus(1, ChronoUnit.YEARS);
            //减少一个月
            localDateTime = localDateTime.minusMonths(1);
            localDateTime = localDateTime.minus(1, ChronoUnit.MONTHS);
            // 使用with修改
            //修改年为2019
            localDateTime = localDateTime.withYear(2020);
            //修改为2022
            localDateTime = localDateTime.with(ChronoField.YEAR, 2022);
        }
        
        // =================== 时间计算 =================
        {
            LocalDate nowDate = LocalDate.now();
            // 取得当前年第一天
            System.out.println(nowDate.with(firstDayOfYear()));
            // 取得当前月份第一天
            System.out.println(nowDate.with(firstDayOfMonth()));
            // 取得当前月份最后一天
            System.out.println(nowDate.with(lastDayOfMonth()));
        }
        
        // =================== 格式化时间（转字串） =================
        {
            LocalDate localDate = LocalDate.of(2019, 9, 10);
            // 输成字串
            String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
            // 20190910
            System.out.println(s1);
            String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            // 2019-09-10
            System.out.println(s2);
            //自定义格式化
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String s3 = localDate.format(dateTimeFormatter);
            // 10/09/2019
            System.out.println(s3);
        }
        
        // =================== 解析时间（字串转时间） =================
        {
            LocalDate localDate1 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
            LocalDate localDate2 = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);
            assert localDate1 == localDate2;
            System.out.println(localDate1);
        }
        
        // =================== 与原有Date互相转换 =================
        {
            // 从 Date 转成 Local
            Date date = new Date();
            Instant instant = date.toInstant();
            // 取得系统默认区划ID
            ZoneId zoneId = ZoneId.systemDefault();
            // 取得东八区
            ZoneId gmt8 = ZoneId.of("+8");
            // 方法1 ofInstant
            LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant, zoneId);
            // 方法1 ZonedDateTime.toLocalDateTime
            LocalDateTime localDateTime2 = instant.atZone(zoneId).toLocalDateTime();
            
            // 从 Local 转成 Date
            Date dateBack = Date.from(localDateTime1.atZone(zoneId).toInstant());
            
        }
    }
    
    /**
     * 日期差
     * REF：https://blog.csdn.net/u012240455/article/details/89670013
     */
    @Test
    public void dateDiffTest() {
        LocalDate from = LocalDate.of(2018, 5, 12);
        LocalDate to = LocalDate.of(2021, 6, 17);
    
        System.out.println(" ========== ChronoUnit，推荐 ========== ");
        System.out.println(ChronoUnit.DAYS.between(from, to));
        System.out.println(ChronoUnit.MONTHS.between(from, to));
        System.out.println(ChronoUnit.YEARS.between(from, to));
        // 输出
        // 1132
        // 37
        // 3
    
        System.out.println(" ========== Period 不好用!!! ========== ");
        Period between = Period.between(from, to);
        System.out.println("天：" + between.getDays());
        System.out.println("月：" + between.getMonths());
        System.out.println("年：" + between.getYears());
        // 只有输出,单个
        // 天：5
        // 月：1
        // 年：3
        
        // 相差多少天，转成 toEpochDay
        System.out.println(to.toEpochDay() - from.toEpochDay());
        
        System.out.println(" ========== Duration 类，提供了使用基于时间的值（如秒，纳秒）测量时间量的方法。 ========== ");
        Instant instBeg = Instant.now();
        Instant instEnd = instBeg.plus(2, ChronoUnit.MINUTES).plus(10, ChronoUnit.SECONDS).plus(10, ChronoUnit.NANOS);
        System.out.println(instBeg);
        System.out.println(instEnd);
        Duration duration = Duration.between(instBeg, instEnd);
        System.out.println("相差秒数（不包含纳秒）：" + duration.getSeconds());
        System.out.println("相差纳秒数（不包含秒）：" + duration.getNano());
        System.out.println(duration.getUnits());
        
        
    }
    
}

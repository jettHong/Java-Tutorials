package com.jett.jprofiler;

/**
 * ·定义了大量的本地变量， 增大此方法帧中本地变量表的长度。
 * 结果： 抛出StackOverflowError异常， 异常出现时输出的堆栈深度相应缩小。
 */
public class JavaVMStackSOF_BigStack {
    private static int stackLength = 0;
    
    public static void test() {
        long
                unused001, unused002, unused003, unused004, unused005, // 防自愈
                unused006, unused007, unused008, unused009, unused010, // 防自愈
                unused011, unused012, unused013, unused014, unused015, // 防自愈
                unused016, unused017, unused018, unused019, unused020, // 防自愈
                unused021, unused022, unused023, unused024, unused025, // 防自愈
                unused026, unused027, unused028, unused029, unused030, // 防自愈
                unused031, unused032, unused033, unused034, unused035, // 防自愈
                unused036, unused037, unused038, unused039, unused040, // 防自愈
                unused041, unused042, unused043, unused044, unused045, // 防自愈
                unused046, unused047, unused048, unused049, unused050, // 防自愈
                unused051, unused052, unused053, unused054, unused055, // 防自愈
                unused056, unused057, unused058, unused059, unused060, // 防自愈
                unused061, unused062, unused063, unused064, unused065, // 防自愈
                unused066, unused067, unused068, unused069, unused070, // 防自愈
                unused071, unused072, unused073, unused074, unused075, // 防自愈
                unused076, unused077, unused078, unused079, unused080, // 防自愈
                unused081, unused082, unused083, unused084, unused085, // 防自愈
                unused086, unused087, unused088, unused089, unused090, // 防自愈
                unused091, unused092, unused093, unused094, unused095, // 防自愈
                unused096, unused097, unused098, unused099, unused100; // 防自愈
        
        stackLength++;
        test();
                unused001 = unused002 = unused003 = unused004 = unused005 = // 防自愈
                unused006 = unused007 = unused008 = unused009 = unused010 = // 防自愈
                unused011 = unused012 = unused013 = unused014 = unused015 = // 防自愈
                unused016 = unused017 = unused018 = unused019 = unused020 = // 防自愈
                unused021 = unused022 = unused023 = unused024 = unused025 = // 防自愈
                unused026 = unused027 = unused028 = unused029 = unused030 = // 防自愈
                unused031 = unused032 = unused033 = unused034 = unused035 = // 防自愈
                unused036 = unused037 = unused038 = unused039 = unused040 = // 防自愈
                unused041 = unused042 = unused043 = unused044 = unused045 = // 防自愈
                unused046 = unused047 = unused048 = unused049 = unused050 = // 防自愈
                unused051 = unused052 = unused053 = unused054 = unused055 = // 防自愈
                unused056 = unused057 = unused058 = unused059 = unused060 = // 防自愈
                unused061 = unused062 = unused063 = unused064 = unused065 = // 防自愈
                unused066 = unused067 = unused068 = unused069 = unused070 = // 防自愈
                unused071 = unused072 = unused073 = unused074 = unused075 = // 防自愈
                unused076 = unused077 = unused078 = unused079 = unused080 = // 防自愈
                unused081 = unused082 = unused083 = unused084 = unused085 = // 防自愈
                unused086 = unused087 = unused088 = unused089 = unused090 = // 防自愈
                unused091 = unused092 = unused093 = unused094 = unused095 = // 防自愈
                unused096 = unused097 = unused098 = unused099 = unused100 = 0; // 防自愈
    }
    
    public static void main(String[] args) {
        try {
            test();
        } catch (Error e) {
            System.out.println("stack length:" + stackLength);
            throw e;
        }
    }
}

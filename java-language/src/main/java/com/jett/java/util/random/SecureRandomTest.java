package com.jett.java.util.random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * java.util.Random的实例不是加密安全的，SecureRandom来获取加密安全的伪随机数生成器，以供对安全敏感的应用程序使用。
 *
 * @author jett
 */
public class SecureRandomTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        
        SecureRandom secureRandom1 = SecureRandom.getInstance("SHA1PRNG");
        secureRandom1.setSeed(10000L);
        SecureRandom secureRandom2 = SecureRandom.getInstance("SHA1PRNG");
        secureRandom2.setSeed(10000L);
        
        for (int i = 0; i < 10; i++) {
            System.out.println(secureRandom1.nextInt());
            int i1 = secureRandom1.nextInt();
            int i2 = secureRandom2.nextInt();
            System.out.println(i1 + " = " + i2 + " => " + (i1 == i2));
        }
    
    
    }
}

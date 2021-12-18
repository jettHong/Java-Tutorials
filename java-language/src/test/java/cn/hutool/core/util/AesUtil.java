/**
 * Project Name:4a-cas-server
 * File Name:AesUtil.java
 * Package Name:com.iwhalecloud.cas.encoder
 * Date:2018年12月5日下午12:16:49
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 */

package cn.hutool.core.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName:AesUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:   TODO ADD REASON. <br/>
 * Date:     2018年12月5日 下午12:16:49 <br/>
 * @author liang
 * @version
 * @since JDK 1.8
 * @see
 */
public class AesUtil {
    

    private static String Algorithm = "AES";
    /**
    * 算法/模式/补码方式
     */
    private static String AlgorithmProvider = "AES/ECB/PKCS5Padding";
    
    /**
     * aes加密
     */
    public static String encryptAes(String content, String key) {
        // 将key hash转为16个字节
        key = hashTo16Byte(key);
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), Algorithm);
            // "算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(AlgorithmProvider);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
           byte[] bytes= cipher.doFinal(content.getBytes("UTF-8"));
            return  parseByte2HexStr(bytes) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    

    /**
     * 将byte数组转换成16进制String
     * @param buf
     * @return
     */

    public static String parseByte2HexStr(byte buf[]) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < buf.length; i++) {

            String hex = Integer.toHexString(buf[i] & 0xFF);

            if (hex.length() == 1) {

                hex = '0' + hex;

            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static String hashTo16Byte(String key) {
        //用于加密的字符
        char[] md5String= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = key.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                //95
                byte byte0 = md[i];
                //    5
                str[k++] = md5String[byte0 >>> 4 & 0xf];
                //   F
                str[k++] = md5String[byte0 & 0xf];
            }
            //返回经过加密后的字符串
            return new String(str).substring(8,24);

        } catch (Exception e) {
            return null;
        }
    }

    // 示例
    public static void main(String[] args) throws NoSuchAlgorithmException {
        /*
x-auth-appGuid	应用guid，暂时由中台这边提供,后续会在界面看到
#智慧财政在统一平台创建的应用appid
a8488003-61b5-4eb3-9ae3-7e91f2d4526d

App Key x-auth-appKey	应用App Key，在统一门户技术中台编辑应用界面可以看到,如下图
aae0cc91-ec55-40f3-8c94-b5aea16e212e

App Secret
b10289d6-7269-4d4c-a1c8-015b42d9c6cc

        * */
        String appID = "a8488003-61b5-4eb3-9ae3-7e91f2d4526d";
        String appKey = "aae0cc91-ec55-40f3-8c94-b5aea16e212e";
        String key = "b10289d6-7269-4d4c-a1c8-015b42d9c6cc";
        String content = appID + " " + appKey;
        String  cipherText  = encryptAes(content, key);
        System.out.println("加密："+cipherText );

        //输出的密文为:6693A6C60F9E5BA5E4186FA5DAF10FF86FD07F5828F75CF0FB17744948290A886803BBA69178470075E395D257A6841D663458192443B7AA3D2ACFCF888686B903061EFB442DC8455C68A59719C0410F
    }


}


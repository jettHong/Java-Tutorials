package cn.hutool.core.util;

import cn.hutool.core.util.IdcardUtil;

/**
 * 身份证号 校验
 *
 * @author jett
 */
public class IdcardUtilTest {
    public static void main(String[] args) {
        
        String[] str = {
                "456413194010250152",
                "438270197201295861",
                "239869201012201424",
                "617278193809179469",
                "214547193712062429",
                "320866197009173827",
                "64866520110402150X",
                "123982197211174058",
                "412234195306219343",
                "15235519710512229X",
        };
        
        for (String s : str) {
            System.out.println(IdcardUtil.isValidCard18(s));
        }
    }
}

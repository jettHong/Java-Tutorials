package com.jett.java.lang;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileGen {
    
    static String[] files = {
            "/12500000450384478B/2021/5/事业单位法人证书__5d1e56fe757e91172fec645ae53644a82c84fe8cf63694c81180a8eb945416d9.jpg",
            "/default/2021/6/法人正本_9383418a498a6a0a45b30726019cbf064944bb961b227227bc5277a07d0a8b65.jpg",
            "/default/2021/5/法人证书_ce91898465a4f4db04db42f5390da9fa0b6b380fce3b6dc8ab0f50db875feae9.jpg",
            "/default/2021/5/法人证书正本扫描件_00c74db2db2686b4a24be94539dfab0ee4d5e95a1ee25225a57a4f00e2b9582b.jpg",
            "/default/2021/5/1.事业单位法人证书_84a08d0019bf3eaef4078f65412cff3bcd8e8c3c7a8fb9ff433dd4f342252252.png",
            "/12500107MB1043977L/2021/5/产研院_73f4b8417ed9912c960c8aeca248625dc202bfe9d42a93b88ec3edc6a2bcf9f8.jpg",
            "/default/2021/3/营业执照_1a5d4d6a74deb03c187f8408e571fa2ed09b2e67b080258a0ccb572d08b38bc4.jpg",
            "/91500000554076154R/2021/6/1622515829008_4a80ffbc6faa2b380f28ba68b1bbb17e34c6691af0800e7dd3c2d8c1e41429ff.jpg",
            "/default/2021/5/正本_e720a1f583ab4406cee5631e6b067dd159d09080e58a21c95464f28ab7a32072.jpg",
            "/915000006608596898/2021/5/mmexport1621408890255_507ef0ce30a3b42a95f967568d7cf03a08f0f047259fb9a893c34f508089595d.jpg",
            "/91500000756237614J/2021/5/营业执照（公章）_e1595d60839977e8692b99dc5db4082213a3a7f56417e697a17ce64b7503c4a4.jpg",
            "/default/2021/6/企业营业执照_40b0d110889ca4b5aa57efa4b4f9142a5a1b3b6be93de04c8b90a8eca25ac2bf.jpg",
            "/default/2021/5/高晋_e34ebfddf6355cce21919c2f9a3e9b464e49f3fd47525ae0b53d70faefa8b0e9.jpg",
            "/91500105339621850B/2021/5/QQ图片20210512181650_4ccd4bb7fdfe4be8760cc9c46567c30026c17c754d9fed182a4049f21c2c6f62.png",
            "/default/2021/5/盖章版2021年营业执照_900ef20cb9fedde26c3f7b339c1cb4d751faa27f0949efd5c21967c447a27841.jpg",
            "/91500106671002744G/2021/6/营业_7f7622e802d6bfe4be2bf7e109b1872ea0e08805ad1336a407d4ac9efdaa1be4.jpg",
            "/default/2021/5/营业执照_f4894a8a022639ac1a72abb7525078c5495a982eab73cbe486ba7a7d273d0188.jpg",
            "/915001066965875157/2021/6/营业执照_aa55a0e86cbfbaca60181587d4cb2153a584f2f5a30f5fe9b6dee8f5e3039e53.png",
            "/91500106MA5U3G3TX9/2021/5/d8db0293c39d9bb417cdc7e4a81b705_fd0bbf5e29956cea63db16153455d801ff76c1b5e5758dcb0b33611f8c5b6048.jpg",
            "/91500106MA60539G1C/2021/5/营业执照副本_00_82ed0595118b77aab344f7c0e04d878d4fb8284ad215ba990cdc0d7d65cccad0.jpg",
            "/91500106MA6092A22E/2021/7/控环科技集团新营业执照_094fb5f98c89a268e9f06e0cc206e5a22bf82608e1a5ab5f908b9bc496ef1819.jpg",
            "/91500106MA60G9AQ6A/2021/5/微信图片_20200319104921_cd4c820b94e2c4c5fac5c938ae84e8c065aba5a9fe5ede32cc3b98206d2b7400.jpg",
            "/91500107202851735F/2021/5/营业执照副本2021_1e1fca3cb80f38153ef54d80dd9f4109b1db463fc18800b963ccc9ab70606656.jpg",
            "/default/2021/4/植恩生物营业执照_2534502a2d6bca62bf4f1232b7a4a44f8d43bc1b45c0fe31c6bd05ec0290c583.png",
            "/default/2021/5/微信图片_20210512103657_bc01c032920b71a07ce2b0c20aa1445cd92207b0c6218a5ce8cddb395aa16a32.png",
            "/default/2021/5/1_bdc7915d569d1d1bfcde3466d905ce60df4f59c35173be93b807852b2d7e1839.png",
            "/default/2021/5/杰品营业执照副本-202104_aaa10d92419731d627e7dfd36cf8044014ccf821e25d130dc50135358d59b4ae.jpg",
            "/default/2021/5/营业执照-派馨特190812_b0529aaa95a3d44ffa0378e088a977805e260d68c91fa66176f54899f99b33c6.jpg",
            "/default/2021/5/营业执照JPG_efdfec34fcb1c8e6ae7a9fd2854770eb910afc0a7cd44f00f26fd5475d879843.jpg",
            "/91500107MA5U4AUN1H/2021/6/Snipaste_2021-06-07_14-16-41_d7c230f0da95b8c6212a70d469eb6898ee1b743e4abc4e6640c5d1717a699a7f.png",
            "/default/2021/6/产研院公司盖章执照_ec064712f8ed85de49519ef236426dc5740f263ea71181b481462b0569fa65d6.jpg",
            "/91500107MA60160W1T/2021/5/营业执照副本2150.37万元_0891d7570f57c15701c7932986f18b903445024138747cf2488fa987ddaa4646.jpg",
            "/91500107MA610GHM04/2021/5/营业执照_c484e47d30668d809d3963dee7f0d8043140172d7da790d7563f8c29fade23b9.jpg",
            "/91500107MA61AKNE98/2021/5/1_52c740aa218cf299affdba4bb8463f98bdeaf77d9e519e6606fe0c1111e57090.png",
            "/default/2021/5/瑞希营业执照20201228_33b7deaa5f4ea62feafbc151e4bd8bf2fbb148b1515f53cc7c56a34b24302386.png"
    };
    public static void main(String[] args) throws IOException {
//        for (String f : files) {
//            File file = new File("D:/Users/Desktop/temp"+f);
//            file.getParentFile().mkdirs();
//            file.createNewFile();
//        }

        String baseDir = "D:/Users/Desktop/temp";
        String newDir  = "D:/Users/Desktop/temp/整理到一个文件夹/";
//        for (String f : files) {
//            File file = new File(baseDir + f);
//            System.out.println(file.isFile());
//            file.renameTo(new File(baseDir + f)); // 需要只传入文件名。
//        }
    
        ExcelReader reader = ExcelUtil.getReader("D:/Users/Desktop/申报人才的企业营业执照.xlsx");
        List<Map<String, Object>> list = reader.readAll();
        System.out.println(list);
        for (Map<String, Object> map : list) {
            String code = MapUtil.getStr(map, "统一社会信用代码");
            String entName = MapUtil.getStr(map, "企业名称");
            File file = new File(baseDir + MapUtil.getStr(map, "FILE_PATH"));
            String name = file.getName();
            String ext = name.substring(name.lastIndexOf("."));
            file.renameTo(new File(newDir + code + "_" + entName + ext));
        }
    }
}

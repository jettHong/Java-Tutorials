package cn.hutool.core.util;

import org.w3c.dom.Document;

public class Str2Xml {
    public static void main(String[] args) {
        String head = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        String str = "<row oss_thumbnail_id=\"\" file_name=\"文件名.pdf\" contract_id=\"206008\" archive_id=\"9E000C2CC0A8017713B45903C59A27C9\" file_size=\"4172\" oss_id=\"c30137651f1d58144f12340c63834afef828bfb2d96ead835725776afe3c3f0f\" /> \n"
                //+ "<row oss_thumbnail_id=\"\" file_name=\"文件名1.pdf\" contract_id=\"206008\" archive_id=\"9E0010D5C0A801774D2826EB82A196DC\" file_size=\"4155\" oss_id=\"e00a32fa2c1ba0124abb2231aba863ac4107fb0e38b02c8f232dca14f6a2ceb3\" />"
                ;
        
        Document document = XmlUtil.parseXml(head + str);
        System.out.println(document.getElementById("row"));
    }
}

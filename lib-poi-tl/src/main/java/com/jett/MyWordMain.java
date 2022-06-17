package com.jett;

import cn.hutool.Hutool;
import cn.hutool.core.convert.NumberChineseFormatter;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import org.apache.poi.sl.usermodel.PictureData;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MyWordMain {
    
    RowRenderData header;
    List<RowRenderData> tableDatas;
    
    public static void main(String[] args) throws IOException {
        MyWordMain myWordMain = new MyWordMain();
        myWordMain.run();
    }
    
    private void run() throws IOException {
        Double money = 13001234.90D;
        XWPFTemplate template = XWPFTemplate
                .compile(MyWordMain.class.getClassLoader().getResourceAsStream("template.docx"))
                .render(
                        new HashMap<String, Object>() {{
                            put("资金处室", "厦门市工业和信息化局国富民强科室");
                            put("扶持对象名称", "厦门市普天同庆工作室连带责任公司");
                            put("收款收据金额大写", NumberChineseFormatter.format(money, false, true));
                            put("收款收据金额", BigDecimal.valueOf(money));
                            put("收款单位全称", "厦门市普天同庆工作室连带责任公司");
                            put("开户名", "普天同庆开户名");
                            put("开户银行", "中国宇宙银行(厦门湖滨北支行)");
                            //
                            put("账号", "9879871230912389873");
                            put("联系人", "李大强");
                            put("联系电话", "13800000000");
                            put("统一社会信用代码", "913205940881400000");
                            put("身份证号码", "816645202201010001");
                            // 水印图片
                            put("streamImg",
                                    new PictureRenderData(80, 100, PictureData.PictureType.PNG.extension,
                                            MyWordMain.class.getClassLoader().getResourceAsStream("科技局.png"))
                            );
                            // 表格
                        }});
        
        template.writeToFile("output.docx");
        
        
    }
    
    
    public void init() {
        header = new RowRenderData(
                Arrays.asList(new TextRenderData("FFFFFF", "Word处理解决方案"),
                        new TextRenderData("FFFFFF", "是否跨平台"), new TextRenderData("FFFFFF", "易用性")),
                "ff9800");
        RowRenderData row0 = RowRenderData.build("Poi-tl", "纯Java组件，跨平台", "简单：模板引擎功能，并对POI进行了一些封装");
        RowRenderData row1 = RowRenderData.build("Apache Poi", "纯Java组件，跨平台", "简单，缺少一些功能的封装");
        RowRenderData row2 = RowRenderData.build("Freemarker", "XML操作，跨平台", "复杂，需要理解XML结构");
        RowRenderData row3 = RowRenderData.build("OpenOffice", "需要安装OpenOffice软件",
                "复杂，需要了解OpenOffice的API");
        RowRenderData row4 = RowRenderData.build("Jacob、winlib", "Windows平台", "复杂，不推荐使用");
        tableDatas = Arrays.asList(row0, row1, row2, row3, row4);
    }
    
}

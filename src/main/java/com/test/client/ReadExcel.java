package com.test.client;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ReadExcel {


    /**
     * 读取excel 获取执行步骤
     * @return
     */
    public List<Map<Integer,String>> getStep() {
        List<Map<Integer, String>> result = new ArrayList<>();

        URL resource = this.getClass().getResource("/auto_web.xls");

        File file = new File(resource.getFile());
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));

            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                Map<Integer, String> map = new HashMap<>();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    map.put(j, row.getCell(j).getStringCellValue());
                }
                result.add(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}

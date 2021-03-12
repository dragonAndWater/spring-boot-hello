package com.example.demo.util;

import com.alibaba.excel.EasyExcelFactory;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class EasyExcelUtil {

    /**
     * @Author longtao
     * @Date   2021/3/8
     * @Describe 传入文件流，解析excel对象，读取的sheet，首行数据rowNum
     **/
    public static List<T> syncReadModel(InputStream inputStream, Class clazz, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }

    public static List<T> syncReadModel(File file, Class clazz, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(file).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }
}

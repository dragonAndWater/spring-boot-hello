package com.example.demo.executer.readerBlackList.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class BlackListExcel {
    @ExcelProperty(value = "读者id", index = 0) // 定义表头名称和位置,0代表第一列
    private String readerId;
    @ExcelProperty(value = "读者姓名", index = 1)
    private String readerName;
    @ExcelProperty(value = "黑名单标识", index = 2)//0-黑名单，1-白名单
    private String blackFlag;
}

package com.example.demo.executer.bookInfo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookInfoExcelModel {
    @ExcelProperty(value = "书籍名称", index = 0) // 定义表头名称和位置,0代表第一列
    private String bookName;
    @ExcelProperty(value = "书籍价格", index = 1)
    private BigDecimal bookPrice;
    @ExcelProperty(value = "书籍作者", index = 2)
    private String bookAuther;
    @ExcelProperty(value = "书籍类别", index = 3)
    private String bookType;
    @ExcelProperty(value = "珍本标识", index = 4)
    private String rareFlag;
    @ExcelProperty(value = "出版社", index = 5)
    private String press;
    @ExcelProperty(value = "出版日期", index = 6)
    private Date pressDate;
    @ExcelProperty(value = "备注", index = 7)
    private String remark;
}

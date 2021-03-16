package com.example.demo.executer.exportExcel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * @author zyf
 * @date 2021/3/2
 */
@Data
@ContentRowHeight(10)
@HeadRowHeight(20)
@ColumnWidth(25)
public class CustEnterpriseExcel {
    // 主键id
    @ExcelIgnore
    private Long id;
    @ColumnWidth(40)
    @ExcelProperty(value = "企业名称", index = 1)
    private String name;
    @ColumnWidth(20)
    @ExcelProperty(value = "信用代码", index = 2)
    private String companyCode;
    @ColumnWidth(40)
    @ExcelProperty(value = "上级公司", index = 3)
    private String companyPName;
    @ColumnWidth(20)
    @ExcelProperty(value = "上级公司信用代码", index = 4)
    private String companyPCode;
    @ColumnWidth(20)
    @ExcelProperty(value = "企业联系人", index = 5)
    private String contactName;
    @ColumnWidth(20)
    @ExcelProperty(value = "联系人电话", index = 6)
    private String phone;
    @ColumnWidth(40)
    @ExcelProperty(value = "地址", index = 7)
    private String address;
    @ColumnWidth(20)
    @ExcelProperty(value = "企业LOGO", index = 8)
    private String logo;
    @ColumnWidth(10)
    @ExcelProperty(value = "省份", index = 9)
    private String province;
    @ColumnWidth(10)
    @ExcelProperty(value = "市", index = 10)
    private String city;
}

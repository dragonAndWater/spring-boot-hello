package com.example.demo.demoTest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewModel {
    private String name;
    private Integer age;
    private BigDecimal salary;
    private String remark;

    @Override
    public String toString() {
        return "NewModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", remark='" + remark + '\'' +
                '}';
    }
}

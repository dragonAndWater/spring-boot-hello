package com.example.demo.demoTest;

import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class TestDemo {
    public static void main(String[] args) {
        OldModel oldModel = new OldModel();
        oldModel.setAge(18);
        oldModel.setName("zhangsan");
        oldModel.setSalary(new BigDecimal(500.00));
        oldModel.setRemark("00.....00");

        NewModel newModel = new NewModel();
        BeanUtils.copyProperties(oldModel,newModel);
        System.out.println("oldModel = "+oldModel);
        System.out.println("newModel = "+newModel.toString());

    }
}

package com.example.demo.util.excelUtil;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelPullDownConstraint {
    String[] source() default {};

    Class[] sourceClass() default {};
}
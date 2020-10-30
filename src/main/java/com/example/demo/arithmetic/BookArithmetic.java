package com.example.demo.arithmetic;

import com.example.demo.base.Enum.FeeTypeEnum;
import com.example.demo.base.Enum.RareFlagEnum;
import com.example.demo.util.dateUtile.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author longtao
 * @Date 2020/10/27
 * @Describe 书籍算法类
 * 1:计算借阅金额
 * 2:计算丢失书籍赔偿
 * <p>
 * BigDecimal运算符
 * 加法   .add
 * 减法   .subtract
 * 乘法   .multiply
 * 除法   .divide
 **/
@Slf4j
@Configuration
public class BookArithmetic {
    /**
     * 按照小时：免费小时
     **/
    @Value("${bookArithmetic.freeHour}")
    private int freeHour;
    /**
     * 按照小时：最大计费小时
     **/
    @Value("${bookArithmetic.maxHour}")
    private int maxHour;
    /**
     * 按照天：免费天数
     **/
    @Value("${bookArithmetic.freeDay}")
    private int freeDay;
    /**
     * 按照天：最大计费天数
     **/
    @Value("${bookArithmetic.maxDay}")
    private int maxDay;
    /**
     * 按照月数：免费月数
     **/
    @Value("${bookArithmetic.freeMonth}")
    private int freeMonth;
    /**
     * 按照月数：最大计费月数
     **/
    @Value("${bookArithmetic.maxMonth}")
    private int maxMonth;
    /**
     * 按照月数：最大计费月数
     **/
    @Value("${bookArithmetic.rareMultiple}")
    private int rareMultiple;
    /**
     * 按照月数：最大计费月数
     **/
    @Value("${bookArithmetic.depreciationYear}")
    private int depreciationYear;

    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe 根据 计费单位、计费日期单位、借阅起始日期、借阅截止日期
     **/
    public synchronized BigDecimal getBorrowAmt(BigDecimal fee, String feeType, Date startDate, Date endDate) {
        BigDecimal borrowAmt = new BigDecimal(0.00);
        switch (feeType) {
            case FeeTypeEnum.DAY:
                //获取计费时长  单位天
                int days = DateUtil.getIntervalDays(startDate, endDate);
                borrowAmt = days <= freeDay ? new BigDecimal(0.00) : days - freeDay > maxDay ? fee.multiply(new BigDecimal(maxDay)) : fee.multiply(new BigDecimal(days - freeDay));
                break;
            case FeeTypeEnum.HOUR:
                //获取计费时长  单位小数
                int hours = DateUtil.getIntervalHours(startDate, endDate);
                borrowAmt = hours <= freeHour ? new BigDecimal(0.00) : hours - freeHour > maxHour ? fee.multiply(new BigDecimal(maxHour)) : fee.multiply(new BigDecimal(hours - freeHour));
                break;
            case FeeTypeEnum.MONTH:
                //获取计费时长  单位月份
                int months = DateUtil.getIntervalMonth(startDate, endDate) + 1;
                borrowAmt = months <= freeMonth ? new BigDecimal(0.00) : months - freeMonth > maxMonth ? fee.multiply(new BigDecimal(maxMonth)) : fee.multiply(new BigDecimal(months - freeMonth));
                break;
        }
        return borrowAmt;
    }

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 遗失书籍赔偿
     * 书籍价格、是否珍本、出版日期
     * 使用时间：（当前日期-出版日期） 单位年
     * 珍本书籍：书籍价格*5   珍本书籍价格很贵
     * 非珍本书籍：书籍价格-折旧费（折旧费=价格/10*（使用时间））
     **/
    public synchronized BigDecimal getLoseAmt(String rareFlag, BigDecimal bookPrice, Date startDate, Date endDate) {
        BigDecimal loseAmt;
        if (RareFlagEnum.RARE_FLAG.getCode().equals(rareFlag)) {
            loseAmt = bookPrice.multiply(new BigDecimal(rareMultiple));
        } else {
            int years = DateUtil.getIntervalYear(startDate, endDate);
            int oldYear = depreciationYear - years >= 0 ? years : depreciationYear;
            loseAmt = bookPrice.subtract(bookPrice.divide(new BigDecimal(depreciationYear)).multiply(new BigDecimal(oldYear)));
        }
        return loseAmt;
    }


}

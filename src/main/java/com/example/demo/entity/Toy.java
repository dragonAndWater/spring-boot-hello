package com.example.demo.entity;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class Toy {
    @NotNull(message = "toyName 不能为null")
    private String toyName;
    @NotNull(message = "toyPrice 不能为null ")
    private String toyPrice;
    @DecimalMax(value = "10000.00")
    private BigDecimal price;

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public String getToyPrice() {
        return toyPrice;
    }

    public void setToyPrice(String toyPrice) {
        this.toyPrice = toyPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

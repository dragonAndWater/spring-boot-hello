package com.example.demo.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@ToString
public class Book {
    @NonNull
    private String book;
    @NonNull
    private BigDecimal price;

}

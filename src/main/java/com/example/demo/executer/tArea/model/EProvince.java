package com.example.demo.executer.tArea.model;

import lombok.Data;

import java.util.List;

@Data
public class EProvince {
    private Long id;
    String code;
    String name;
    List<ECity> children;
}

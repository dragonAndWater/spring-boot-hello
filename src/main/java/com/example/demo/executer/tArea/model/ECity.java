package com.example.demo.executer.tArea.model;

import lombok.Data;

import java.util.List;

@Data
public class ECity {
    private Long id;
    private String code;
    private String name;
    private List<EArea>children;
}

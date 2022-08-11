package com.example.zktraining.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "manufacturer")
    private String manufacturer; // hãng xe

    @Column(name = "name")
    private String name; // tên xe

    @Column(name = "style")
    private String style; // chủng loại xe

    @Column(name = "price")
    private long price; // giá xe

    @Column(name = "engine")
    private long engine; // công suất xe - cc

    @Column(name = "assembly")
    private String assembly; // nơi sản xuất

    @Column(name = "color")
    private String color; // màu xe

    @Column(name = "available")
    private boolean available; // còn hàng hay không
}

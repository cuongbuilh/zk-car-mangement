package com.example.zktraining.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company")
    private String company;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @Column(name = "cubic")
    private String cubic;

    @Column(name = "whereProduction")
    private String whereProduction;

    @Column(name = "color")
    private String color;

    @Column(name = "status")
    private String status;
}

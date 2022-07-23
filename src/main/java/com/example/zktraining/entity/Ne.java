package com.example.zktraining.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;
}

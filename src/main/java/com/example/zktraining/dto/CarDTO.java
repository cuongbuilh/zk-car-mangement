package com.example.zktraining.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Integer id;
    private String company;
    private String name;
    private String category;
    private Double price;
    private String cubic;
    private String whereProduction;
    private String color;
    private String status;
}

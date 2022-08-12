package com.example.zktraining.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarDTO {
    Long id;

    String name;

    Double price;

    String color;

    String model;

    String brand;

    Boolean status;

    String manufacture;

    Boolean checked = false;
}

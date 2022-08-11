package com.example.zktraining.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDTO implements Serializable {
    private int id;
    private String manufacturer;
    private String name;
    private String style;
    private long price;
    private long engine;
    private String assembly;
    private String color;
    private boolean available;
    private boolean checked = false;
}

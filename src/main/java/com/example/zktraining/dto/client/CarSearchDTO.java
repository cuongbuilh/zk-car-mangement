package com.example.zktraining.dto.client;

import lombok.Data;

@Data
public class CarSearchDTO {
    private Integer id;
    private String manufacturer;
    private String name;
    private String style;
    private Long priceFrom;
    private Long priceTo;
    private Long engineFrom;
    private Long engineTo;
    private String assembly;
    private String color;
    private Boolean available;
}

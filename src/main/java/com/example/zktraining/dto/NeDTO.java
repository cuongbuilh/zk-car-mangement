package com.example.zktraining.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NeDTO {
    private Integer id;
    private String name;
    private boolean checked;
}

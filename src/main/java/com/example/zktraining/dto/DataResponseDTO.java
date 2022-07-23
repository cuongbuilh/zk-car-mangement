package com.example.zktraining.dto;

import lombok.Data;

import java.util.List;

@Data
public class DataResponseDTO {
    private List<NeDTO> listNe;
    private Integer totalNe;
}

package com.example.zktraining.service;

import com.example.zktraining.dto.DataResponseDTO;
import com.example.zktraining.dto.NeDTO;
import com.example.zktraining.entity.Ne;

import java.util.List;

public interface NeService {
    DataResponseDTO getListNe(Integer activePage, Integer activeSize);
    Integer getTotalNE();
    void deleteNe(Boolean checkAll, List<Integer> neIds);
}

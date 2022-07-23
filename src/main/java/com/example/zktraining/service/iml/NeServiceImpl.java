package com.example.zktraining.service.iml;

import com.example.zktraining.dto.DataResponseDTO;
import com.example.zktraining.dto.NeDTO;
import com.example.zktraining.entity.Ne;
import com.example.zktraining.mapper.BaseMapper;
import com.example.zktraining.repo.NeRepo;
import com.example.zktraining.service.NeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service(value = "neService")
public class NeServiceImpl implements NeService {

    @Autowired
    private NeRepo neRepo;

    protected BaseMapper<Ne, NeDTO> mapper = new BaseMapper<Ne, NeDTO>(Ne.class, NeDTO.class);

    @Override
    public DataResponseDTO getListNe(Integer activePage, Integer activeSize) {
        Pageable pageable = PageRequest.of(activePage, activeSize);
        Page<Ne> dataNe = neRepo.findAll(pageable);
        //result.getTotalElements();
        DataResponseDTO result = new DataResponseDTO();
        result.setListNe(mapper.toDtoBean(dataNe.getContent()));
        result.setTotalNe(Integer.valueOf(String.valueOf(dataNe.getTotalElements())));
        return result;
    }

    @Override
    public Integer getTotalNE() {
        return null;
    }
}

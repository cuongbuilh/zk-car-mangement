package com.example.zktraining.service;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.dto.client.CarSearchDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> search(CarSearchDTO search);
    boolean addCar(CarDTO carDTO);
    boolean updateCar(CarDTO carDTO);
    boolean deleteCars(List<Integer> ids);
    CarDTO getCarById(int id);
}

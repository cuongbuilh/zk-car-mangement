package com.example.zktraining.service;


import com.example.zktraining.dto.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getListCar();
    CarDTO addCar(CarDTO carDTO);
    void deleteCar(List<Integer> id);
}

package com.example.zktraining.service.iml;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.repo.CarRepo;
import com.example.zktraining.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "carService")
public class CarServiceImp implements CarService {
    @Autowired
    CarRepo carRepo;

    @Override
    public List<CarDTO> getListCar() {
        return carRepo.findAll().stream().map(car -> {
            CarDTO dto = new CarDTO();
            dto.setStatus(car.getStatus());
            dto.setPrice(car.getPrice());
            dto.setName(car.getName());
            dto.setBrand(car.getBrand());
            dto.setColor(car.getColor());
            dto.setId(car.getId());
            dto.setModel(car.getModel());
            dto.setManufacture(car.getManufacture());
            return dto;
        }).collect(Collectors.toList());
    }
}

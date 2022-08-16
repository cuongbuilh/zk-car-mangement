package com.example.zktraining.service.iml;

import java.util.List;
import java.util.Optional;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.entity.Car;
import com.example.zktraining.mapper.BaseMapper;
import com.example.zktraining.repo.CarRepo;
import com.example.zktraining.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "carService")
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepo carRepo;

    protected BaseMapper<Car, CarDTO> mapper = new BaseMapper<Car, CarDTO>(Car.class, CarDTO.class);

    @Override
    public List<CarDTO> getListCar() {
        List<Car> listCar = carRepo.findAll();
        return mapper.toDtoBean(listCar);
    }

    @Override
    public CarDTO addCar(CarDTO carDTO) {
        Car car = mapper.toPersistenceBean(carDTO);
        Car result = carRepo.save(car);
        if(result != null){
            return carDTO;
        }
        return null;
    }

    @Override
    public void deleteCar(List<Integer> id) {
        carRepo.deleteAllById(id);
        }
}

package com.example.zktraining.service.iml;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.dto.client.CarSearchDTO;
import com.example.zktraining.entity.Car;
import com.example.zktraining.mapper.BaseMapper;
import com.example.zktraining.repo.CarRepository;
import com.example.zktraining.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value = "carService")
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final BaseMapper<Car, CarDTO> carMapper = new BaseMapper<>(Car.class, CarDTO.class);

    @Override
    public Page<CarDTO> search(CarSearchDTO search) {
        PageRequest pageRequest = PageRequest.of(search.getPage(), search.getSize());
        return carRepository.search(search, pageRequest).map(carMapper::toDtoBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCar(CarDTO carDTO) {
        Car car = carMapper.toPersistenceBean(carDTO);
        try {
            carRepository.save(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCar(CarDTO carDTO) {
        Optional<Car> optional = carRepository.findById(carDTO.getId());
        if (optional.isPresent()) {
            Car updatedCar = carMapper.toPersistenceBean(carDTO);
            updatedCar.setId(optional.get().getId());
            carRepository.save(updatedCar);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCars(List<Integer> ids) {
        try {
            carRepository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CarDTO getCarById(int id) {
        Optional<Car> option = carRepository.findById(id);
        return option.isPresent() ? carMapper.toDtoBean(option.get()) : new CarDTO();
    }
}

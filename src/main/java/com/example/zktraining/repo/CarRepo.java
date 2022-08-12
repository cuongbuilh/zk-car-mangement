package com.example.zktraining.repo;

import com.example.zktraining.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Integer> {

}

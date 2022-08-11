package com.example.zktraining.repo;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.dto.client.CarSearchDTO;
import com.example.zktraining.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("select c from Car c " +
            "where (:#{#search.id} is null or concat(c.id,'') like %:#{#search.id}% ) " +
            "and (:#{#search.manufacturer} is null or c.manufacturer like %:#{#search.manufacturer}%) " +
            "and (:#{#search.name} is null or c.name like %:#{#search.name}%) " +
            "and (:#{#search.style} is null or c.style like %:#{#search.style}%) " +
            "and (:#{#search.priceFrom} is null or c.price >= :#{#search.manufacturer}) " +
            "and (:#{#search.priceTo} is null or c.price <= :#{#search.manufacturer}) " +
            "and (:#{#search.engineFrom} is null or c.engine >= :#{#search.manufacturer}) " +
            "and (:#{#search.engineTo} is null or c.engine <= :#{#search.manufacturer}) " +
            "and (:#{#search.assembly} is null or c.assembly like %:#{#search.manufacturer}%) " +
            "and (:#{#search.color} is null or c.color like %:#{#search.manufacturer}%) " +
            "and (:#{#search.available} is null or c.available = :#{#search.available}) ")
    List<Car> search(@Param("car") CarSearchDTO search);
}

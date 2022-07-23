package com.example.zktraining.repo;

import com.example.zktraining.entity.Ne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeRepo extends JpaRepository<Ne, Integer> {

}

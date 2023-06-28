package com.memposit.example.mapia.carservice.persistence.repository;

import com.memposit.example.mapia.carservice.persistence.domain.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
}

package com.memposit.example.mapia.carservice.service;

import com.memposit.example.mapia.carservice.dto.CarDto;

import java.util.Collection;

public interface CarService {

    Collection<CarDto> getAll();
}

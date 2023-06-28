package com.memposit.example.mwa.carservice.service;

import com.memposit.example.mwa.carservice.dto.CarDto;

import java.util.Collection;

public interface CarService {

    Collection<CarDto> getAll();
}

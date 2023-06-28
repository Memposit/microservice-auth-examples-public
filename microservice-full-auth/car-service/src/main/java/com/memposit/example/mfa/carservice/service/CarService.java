package com.memposit.example.mfa.carservice.service;

import com.memposit.example.mfa.carservice.dto.CarDto;

import java.util.Collection;

public interface CarService {

    Collection<CarDto> getAll();
}

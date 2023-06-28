package com.memposit.example.mfa.carservice.service.impl;

import com.memposit.example.mfa.carservice.dto.CarDto;
import com.memposit.example.mfa.carservice.mapper.CarMapper;
import com.memposit.example.mfa.carservice.persistence.repository.CarRepository;
import com.memposit.example.mfa.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    private final CarMapper mapper;

    @Override
    public Collection<CarDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }
}

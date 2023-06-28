package com.memposit.example.mfa.carservice.controller;

import com.memposit.example.mfa.carservice.dto.CarDto;
import com.memposit.example.mfa.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping()
    public Collection<CarDto> getCars() {
        return carService.getAll();
    }
}

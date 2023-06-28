package com.memposit.example.mapia.gateway.controller;

import com.memposit.example.mapia.gateway.feign.CarClient;
import com.memposit.example.mapia.gateway.model.Car;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
class PublicApiCarController {

    private final CarClient carClient;

    private Collection<Car> fallback() {
        return new ArrayList<>();
    }

    @GetMapping("/car")
    @CrossOrigin
    @HystrixCommand(fallbackMethod = "fallback")
    public Collection<Car> goodCars() {
        return carClient.readCars()
                .stream()
                .filter(this::isWithoutSpaces)
                .collect(Collectors.toList());
    }

    private boolean isWithoutSpaces(Car car) {
        return !car.getName().contains(" ");
    }
}

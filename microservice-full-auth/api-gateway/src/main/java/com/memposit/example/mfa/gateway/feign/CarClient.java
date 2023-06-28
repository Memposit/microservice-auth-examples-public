package com.memposit.example.mfa.gateway.feign;

import com.memposit.example.mfa.gateway.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient("car-service")
public interface CarClient {

    @GetMapping("/car")
    @CrossOrigin
    Collection<Car> readCars();
}

package com.memposit.example.mfa.carservice;

import com.memposit.example.mfa.carservice.persistence.domain.CarEntity;
import com.memposit.example.mfa.carservice.persistence.repository.CarRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class CarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CarRepository repository) {
        return args -> Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti", "AMC Gremlin", "Triumph Stag", "Ford Galaxy", "Yugo GV")
                .forEach(name -> {
                    CarEntity car = new CarEntity();
                    car.setName(name);
                    repository.save(car);
                });
    }
}


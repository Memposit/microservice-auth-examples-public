package com.memposit.example.mapia.carservice.mapper;

import com.memposit.example.mapia.carservice.dto.CarDto;
import com.memposit.example.mapia.carservice.persistence.domain.CarEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto toDto(CarEntity entity);

    @IterableMapping(elementTargetType = CarDto.class)
    List<CarDto> toDtoList(Collection<CarEntity> entities);
}

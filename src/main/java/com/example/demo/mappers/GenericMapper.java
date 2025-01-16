package com.example.demo.mappers;

import com.example.demo.entities.BaseEntity;
import org.mapstruct.MapperConfig;

import java.util.List;

@MapperConfig(componentModel = "spring")
public interface GenericMapper<E extends BaseEntity, D>{
    
    D toDto(E entity);
    E toEntity(D dto);
    List<D> toDtoList(List<E> entities);
    List<E> toEntityList(List<D> dtos);

}

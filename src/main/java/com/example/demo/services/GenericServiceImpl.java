package com.example.demo.services;

import com.example.demo.entities.BaseEntity;
import com.example.demo.mappers.GenericMapper;
import com.example.demo.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenericServiceImpl<E extends BaseEntity,D, ID> implements IGenericService<E,D, ID> {
    private final GenericRepository<E, ID> genericRepository;
    private final GenericMapper<E, D> genericMapper;

    @Autowired
    public GenericServiceImpl(GenericRepository<E, ID> genericRepository, GenericMapper<E, D> genericMapper) {
        this.genericRepository = genericRepository;
        this.genericMapper = genericMapper;
    }

    @Override
    public D save(D dto) {
        E entity = genericMapper.toEntity(dto);
        E savedEntity = genericRepository.save(entity);
        return genericMapper.toDto(savedEntity);
    }

    @Override
    public List<D> saveAll(List<D> dtos) {
        List<E> entities = dtos.stream().map(genericMapper::toEntity).collect(Collectors.toList());
        List<E> savedEntities = genericRepository.saveAll(entities);
        return savedEntities.stream().map(genericMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<D> findById(ID id) {
        Optional<E> entity = genericRepository.findById(id);
        return entity.map(genericMapper::toDto);
    }

    @Override
    public List<D> findAll() {
        List<E> entities = genericRepository.findAll();
        return entities.stream().map(genericMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<D> findAll(Pageable page) {
        Page<E> entities = genericRepository.findAll(page);
        return entities.map(genericMapper::toDto);
    }

    @Override
    public List<D> findByCriteria(Specification<E> spec) {
        List<E> entities = genericRepository.findAll(spec);
        return entities.stream().map(genericMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<D> findByCriteria(Specification<E> spec, Pageable pageable) {
        Page<E> entities = genericRepository.findAll(spec, pageable);
        return entities.map(genericMapper::toDto);
    }

    @Override
    public void deleteById(ID id) {
        genericRepository.deleteById(id);
    }

    @Override
    public long count() {
        return genericRepository.count();
    }

    @Override
    public void deleteAll() {
        genericRepository.deleteAll();
    }

    @Override
    public void deleteAll(List<D> dtos) {
        List<E> entities = dtos.stream().map(genericMapper::toEntity).collect(Collectors.toList());
        genericRepository.deleteAll(entities);
    }
}

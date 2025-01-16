package com.example.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface IGenericService<E,D, ID> {
    D save(D dto);
    List<D> saveAll(List<D> dtos);
    Optional<D> findById(ID id);
    List<D> findAll();
    Page<D> findAll(Pageable page);
    List<D> findByCriteria(Specification<E> spec);
    Page<D> findByCriteria(Specification<E> spec, Pageable pageable);

    void deleteById(ID id);
    long count();
    void deleteAll();
    void deleteAll(List<D> dtos);

}

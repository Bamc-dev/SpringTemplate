package com.example.demo.repository;

import com.example.demo.entities.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E extends BaseEntity, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
    E save(E entity);
    Optional<E> findById(ID id);
    List<E> findAll();
    Page<E> findAll(Pageable pageable);
    Page<E> findAll(Specification<E> spec, Pageable pageable);
    List<E> findAll(Specification<E> spec);
    void deleteById(ID id);
    void delete(E entity);
    void deleteAll(Iterable<? extends E> entities);
    void deleteAll();
    void deleteAll(Specification<E> spec);
}

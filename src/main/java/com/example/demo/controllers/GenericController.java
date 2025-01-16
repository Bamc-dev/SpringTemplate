package com.example.demo.controllers;

import com.example.demo.entities.BaseEntity;
import com.example.demo.services.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class GenericController<E extends BaseEntity, D, ID> {
    private final IGenericService<E,D,ID> genericService;

    protected GenericController(IGenericService<E, D, ID> genericService) {
        this.genericService = genericService;
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D dto) {
        D createdDto = genericService.save(dto);
        return ResponseEntity.ok(createdDto);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<D>> createAll(@RequestBody List<D> dtos) {
        List<D> createdDtos = genericService.saveAll(dtos);
        return ResponseEntity.ok(createdDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable ID id) {
        Optional<D> dto = genericService.findById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<D>> getAll() {
        List<D> dtos = genericService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<D>> getAll(Pageable pageable) {
        Page<D> dtos = genericService.findAll(pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<D>> search(Specification<E> spec) {
        List<D> dtos = genericService.findByCriteria(spec);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search/page")
    public ResponseEntity<Page<D>> search(Specification<E> spec, Pageable pageable) {
        Page<D> dtos = genericService.findByCriteria(spec, pageable);
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/bulk")
    public ResponseEntity<Void> deleteAll(@RequestBody List<D> dtos) {
        genericService.deleteAll(dtos);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {
        genericService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}

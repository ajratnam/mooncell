package com.cats.mooncell.services;

import com.cats.mooncell.data.User;
import com.cats.mooncell.data.UserRepository;
import com.cats.mooncell.data.Warehouse;
import com.cats.mooncell.data.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository repository;

    public WarehouseService(WarehouseRepository repository) {
        this.repository = repository;
    }

    public Optional<Warehouse> get(Long id) {
        return repository.findById(id);
    }

    public Warehouse update(Warehouse entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Warehouse> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Warehouse> list(Pageable pageable, Specification<Warehouse> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}

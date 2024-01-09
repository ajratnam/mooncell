package com.cats.mooncell.services;

import com.cats.mooncell.data.CurrentOrder;
import com.cats.mooncell.data.CurrentOrderRepository;
import com.cats.mooncell.data.Order;
import com.cats.mooncell.data.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentOrderService {

    private final CurrentOrderRepository repository;

    public CurrentOrderService(CurrentOrderRepository repository) {
        this.repository = repository;
    }

    public Optional<CurrentOrder> get(Long id) {
        return repository.findById(id);
    }

    public CurrentOrder update(CurrentOrder entity) {
        return repository.save(entity);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<CurrentOrder> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<CurrentOrder> list(Pageable pageable, Specification<CurrentOrder> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}

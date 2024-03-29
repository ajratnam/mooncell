package com.cats.mooncell.services;

import com.cats.mooncell.data.Item;
import com.cats.mooncell.data.ItemRepository;
import com.cats.mooncell.data.Order;
import com.cats.mooncell.data.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Optional<Order> get(Long id) {
        return repository.findById(id);
    }

    public Order update(Order entity) {
        return repository.save(entity);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Order> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Order> list(Pageable pageable, Specification<Order> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}

package com.cats.mooncell.services;

import com.cats.mooncell.data.Item;
import com.cats.mooncell.data.ItemRepository;
import com.cats.mooncell.data.User;
import com.cats.mooncell.data.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Optional<Item> get(Long id) {
        return repository.findById(id);
    }

    public Item update(Item entity) {
        return repository.save(entity);
    }
    public List<String> findAllByNameIsNotNull(){
        return repository.findAllByNameIsNotNull();
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Item> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Item> list(Pageable pageable, Specification<Item> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}

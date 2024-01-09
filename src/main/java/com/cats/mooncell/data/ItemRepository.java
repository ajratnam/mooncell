package com.cats.mooncell.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

        Item findByName(String name);
        Item findById(long  id);

        @Query("SELECT i.name FROM Item i WHERE i.name IS NOT NULL")
        List<String> findAllByNameIsNotNull();

}


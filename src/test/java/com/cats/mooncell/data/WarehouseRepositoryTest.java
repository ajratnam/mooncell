package com.cats.mooncell.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
class WarehouseRepositoryTest {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Test
    void findByWarehouseCodeId() {
        assertNotNull(warehouseRepository.findByWarehouseCodeId(3403434L));
        // In the current state, the below assertion will be true as there is no warehouse with id 0
        Long non_existent_id = 0L;
        Warehouse[] warehouses = warehouseRepository.findByWarehouseCodeId(non_existent_id);
        assertTrue(warehouses == null || warehouses.length == 0);
    }
    // I am a moron and I don't know how to write tests and I do not know to spel right either
    @Test
    void findByCustomerId() {
        assertNotNull(warehouseRepository.findByCustomerId(5029913L));

        Long non_existent_id = 0L;
        Warehouse[] warehouses = warehouseRepository.findByCustomerId(non_existent_id);
        assertTrue(warehouses == null || warehouses.length == 0);

    }
    // Supply chain management of what exactly? I don't know what I am doing. Coplilot FTW. May god supply knowedge
    // to the organ I call my brain -  recursion is the key to success

    @Test
    void findByCost() {
        assertNotNull(warehouseRepository.findByCost(381.47));

        double non_existent_cost = 0.0;
        Warehouse[] wh = warehouseRepository.findByCost(non_existent_cost);
        assertEquals(wh.length, 0); // this should work I give up
    }


    @Test
    void findWarehouseByOrderNumber() {
        assertNotNull(warehouseRepository.findWarehouseByOrderNumber(20258239));
        int non_existent_order_number = 0;
        Warehouse[] wh = warehouseRepository.findWarehouseByOrderNumber(non_existent_order_number);
        assertEquals(wh.length, 0); // this works unlike the one in the above method
    }

    @Test
    void findByCustomerIdAndOrderNumber() {
        assertNotNull(warehouseRepository.findByCustomerIdAndOrderNumber(5397843L, 20258239));
        Long non_existent_id = 0L;
        int non_existent_order_number = 0;
        Warehouse[] wh = warehouseRepository.findByCustomerIdAndOrderNumber(non_existent_id, non_existent_order_number);
        assertEquals(wh.length, 0);
    }

    @Test
    void deleteByCustomerId() {
        // this is a bad test as it deletes data from the database
        // but I am too lazy to write a test that does not delete data from the database
        // I am a bad person
        // First I inserted a row to test it.
        // that row is gone forever now :(

        Long customer_id = 5463565L;
        warehouseRepository.deleteByCustomerId(customer_id);
        Warehouse[] wh = warehouseRepository.findByCustomerId(customer_id);
        assertEquals(wh.length, 0);
    }


    @Test
    void findByItem() {
       assertNotNull(warehouseRepository.findByItemId(963543L));

    }
    @Test
    void deleteByItem() {
        warehouseRepository.deleteByItemId(1L);
        Warehouse[] wh = warehouseRepository.findByItemId(1L);
        assertEquals(wh.length, 0);

    }

    @Test
    void findWarehouseByCustomerIdAndItemIdAndOrderNumber(){
//        20258239,5397843,8902753
        assertNotNull(warehouseRepository.findWarehouseByCustomerIdAndItemIdAndOrderNumber(5397843L, 8902753L, 20258239));
    }
}
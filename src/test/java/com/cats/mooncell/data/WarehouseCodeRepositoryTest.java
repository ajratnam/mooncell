package com.cats.mooncell.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
class WarehouseCodeRepositoryTest {
    @Autowired
    private WarehouseCodeRepository warehouseCodeRepository;


    @Test
    void injectedComponentsAreNotNull() {
//        assertNotNull(entityManager);
//        assertNotNull(jdbcTemplate);
        assertNotNull(warehouseCodeRepository);
    }

    @Test
    void findById() {
        assertNotNull(warehouseCodeRepository.findById(34020L));
    }
}
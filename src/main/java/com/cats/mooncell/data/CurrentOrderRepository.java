package com.cats.mooncell.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CurrentOrderRepository extends JpaRepository<CurrentOrder, Long>, JpaSpecificationExecutor<CurrentOrder> {
    @Query(value = "SELECT * FROM current_order WHERE customer_name = ?1", nativeQuery = true)
    List<CurrentOrder> findByUserName(String customer_name);

    @Transactional
    void deleteByCustomerName(String customer_name);
}

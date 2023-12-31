package com.cats.mooncell.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WarehouseCodeRepository extends JpaRepository<WarehouseCode,Long>,
        JpaSpecificationExecutor<WarehouseCode> {
    public  WarehouseCode[] findWarehouseCodeById(Long id);
//    public WarehouseCode s
}

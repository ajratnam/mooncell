package com.cats.mooncell.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface WarehouseRepository extends  JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse>{
    public Warehouse[] findByWarehouseCodeId(Long id);
    public Warehouse[] findByCustomerId(Long id);
    public Warehouse[] findByItemId(Long id);
    public Warehouse[] findByCost(double cost);

    public Warehouse[] findWarehouseByOrderNumber(int orderNumber);
    public Warehouse[] findByCustomerIdAndOrderNumber(Long id, int orderNumber);
    public Warehouse findWarehouseByCustomerIdAndItemIdAndOrderNumber(Long id, Long itemId, int orderNumber);

    public void deleteByCustomerId(Long id);
    public void deleteByItemId(Long id);
    




}

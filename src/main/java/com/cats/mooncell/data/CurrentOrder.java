package com.cats.mooncell.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "current_order")
public class CurrentOrder extends AbstractEntity {
    @Column(name = "cost")
    private Double cost;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "units")
    private double units;
    @Column(name = "warehouse_code")
    private Integer warehouseCode;
    @Column(name = "date")
    private LocalDate date;

    public void setUnits(double units) {
        this.units = units;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public Integer getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(Integer warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
}

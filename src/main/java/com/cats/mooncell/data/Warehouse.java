package com.cats.mooncell.data;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouses")
public class Warehouse extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private WarehouseCode warehouseCode;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "id")
    private Item item;

    private double cost;
    private int units;

    private int order_number;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getOrderNumber() {
        return order_number;
    }

    public void setOrderNumber(int orderNumber) {
        this.order_number = orderNumber;
    }

}

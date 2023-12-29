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
    private  Customer customer;

    private double cost;
    private int units;

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

    public int getOrder_line() {
        return order_line;
    }

    public void setOrder_line(int order_line) {
        this.order_line = order_line;
    }

    private int order_line;

}

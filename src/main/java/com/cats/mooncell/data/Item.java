package com.cats.mooncell.data;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;

@Entity
@Table(name = "items")
public class Item extends AbstractEntity{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getQuantity_left() {
        return quantity_left;
    }

    public void setQuantity_left(double quantity_left) {
        this.quantity_left = quantity_left;
    }

    public Integer getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(Integer warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    String name;
    String description;
    @Column(name = "sell_price")
    double sellPrice;
    @Column(name = "buy_price")
    double buyPrice;
    @Column(name = "quantity_left")
    double quantity_left;
    @Column(name = "warehouse_code")
    Integer warehouseCode;

}

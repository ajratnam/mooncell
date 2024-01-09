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
    String name;
    String description;
    @Column(name = "sell_price")
    double sellPrice;
    @Column(name = "buy_price")
    double buyPrice;
    @Column(name = "quantity_left")
    long quantity_left;
}

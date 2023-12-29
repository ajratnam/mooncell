package com.cats.mooncell.data;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;

@Entity
@Table(name = "warehouse_codes")
public class WarehouseCode extends AbstractEntity{

}

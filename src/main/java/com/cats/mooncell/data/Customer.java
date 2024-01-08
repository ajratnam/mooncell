package com.cats.mooncell.data;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;


@Entity
@Table(name = "customers")

public class Customer extends AbstractEntity{

}

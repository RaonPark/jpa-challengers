package com.raonpark.jpa.entity.product;

import java.sql.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date registeredDate;
}

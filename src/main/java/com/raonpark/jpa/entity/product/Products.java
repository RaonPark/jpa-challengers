package com.raonpark.jpa.entity.product;

import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Products {
    @Id @GeneratedValue
    @Column(name = "PRODUCTS_ID")
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date registeredDate;
}

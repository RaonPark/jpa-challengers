package com.raonpark.jpa.entity.product;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Shipment {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(columnDefinition = "TIME default null", nullable = true)
    @Temporal(TemporalType.TIME)
    private Date shippedDate;
}

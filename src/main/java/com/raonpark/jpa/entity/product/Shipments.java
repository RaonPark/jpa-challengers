package com.raonpark.jpa.entity.product;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Shipments {
    @Id @GeneratedValue
    @Column(name = "SHIPMENTS_ID")
    private Long id;

    @OneToOne(mappedBy = "shipments")
    private Order orders;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @Column(nullable = true)
    @Temporal(TemporalType.TIME)
    private Date shippedDate;
}

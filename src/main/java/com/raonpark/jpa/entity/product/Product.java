package com.raonpark.jpa.entity.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.raonpark.jpa.entity.member.Seller;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "PRODUCTS")
public class Product implements Serializable {
    @Id @GeneratedValue
    @Column(name = "PRODUCTS_ID")
    private Long id;

    @NonNull
    private String name;

    private int price;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date registeredDate;

    @ManyToMany(mappedBy = "products")
    private List<Seller> sellers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERS_ID")
    private Order order;

    public boolean equals(Product product) {
        return (this.name == product.name) && (this.price == product.price) && (this.registeredDate == product.registeredDate);
    }
}

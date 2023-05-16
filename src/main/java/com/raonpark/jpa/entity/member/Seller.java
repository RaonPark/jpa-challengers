package com.raonpark.jpa.entity.member;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.raonpark.jpa.entity.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "products")
@NoArgsConstructor
@RequiredArgsConstructor
@DiscriminatorValue("Seller")
public class Seller extends Member implements Serializable {
    @NonNull
    private String companyName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "SELLER_PRODUCTS",
        joinColumns = @JoinColumn(name = "MEMBER_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUCTS_ID"))
    private List<Product> products = new ArrayList<>();

    public boolean equals(Seller seller) {
        return (seller.getId() == this.getId());
    }
}

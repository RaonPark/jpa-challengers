package com.raonpark.jpa.entity.product;

import java.util.Date;
import java.util.List;

import com.raonpark.jpa.entity.member.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Orders {
    @Id @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Temporal(TemporalType.TIME)
    private Date orderdDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTS_ID")
    private List<Products> products;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SHIPMENTS_ID")
    private Shipments shipments;

    @NonNull
    private Long EA;
}

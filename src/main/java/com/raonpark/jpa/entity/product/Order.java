package com.raonpark.jpa.entity.product;

import java.sql.Date;
import java.util.List;

import com.raonpark.jpa.entity.member.Member;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    
    @Temporal(TemporalType.TIME)
    private Date orderdDate;

    @OneToMany
    @JoinColumn(name = "PRODUCT_ID")
    private List<Product> products;

    @NonNull
    private Long EA;
}

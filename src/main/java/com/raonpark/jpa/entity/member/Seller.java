package com.raonpark.jpa.entity.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NonNull;

@Entity
@DiscriminatorValue("Seller")
public class Seller extends Member {
    @NonNull
    private String companyName;
}

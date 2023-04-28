package com.raonpark.jpa.entity.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Seller")
public class Seller extends Member {
    @NonNull
    private String companyName;
}

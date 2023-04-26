package com.raonpark.jpa.entity.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Admin")
public class Admin extends Member {
    @NonNull
    private String adminKey;
}

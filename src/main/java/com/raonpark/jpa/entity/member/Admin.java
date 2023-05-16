package com.raonpark.jpa.entity.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Admin")
public class Admin extends Member {
    @NonNull
    private String adminKey;
}

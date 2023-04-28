package com.raonpark.jpa.entity.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.raonpark.jpa.entity.product.Orders;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GeneralUser extends Member {
    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date birthDay;
}

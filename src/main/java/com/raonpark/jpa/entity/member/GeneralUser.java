package com.raonpark.jpa.entity.member;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.raonpark.jpa.entity.product.Order;

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
public class GeneralUser extends Member implements Serializable {
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date birthDay;
}

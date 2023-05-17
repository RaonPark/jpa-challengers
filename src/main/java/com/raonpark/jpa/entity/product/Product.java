package com.raonpark.jpa.entity.product;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String thisDate = formatter.format(this.registeredDate);

        return (this.id == product.id) && 
            Objects.equals(this.name, product.name) && 
            (this.price == product.price) && 
            Objects.equals(thisDate, product.registeredDate.toString()) &&
            Objects.equals(this.order, product.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, registeredDate, order);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String thisDate = formatter.format(this.registeredDate);
        
        return "id=" + this.id + 
            ", name=" + this.name + 
            ", price=" + this.price + 
            ", registeredDate=" + thisDate + 
            ", order=" + this.order;
    }
}

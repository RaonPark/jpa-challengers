package com.raonpark.jpa.service;

import com.raonpark.jpa.entity.member.Member;
import com.raonpark.jpa.entity.member.Seller;
import com.raonpark.jpa.entity.product.Product;

public interface ProductService {
    boolean registerProduct(Member member, Product products);
    Product getProductByName(Seller seller, String name);
}

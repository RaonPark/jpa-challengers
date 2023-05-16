package com.raonpark.jpa.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raonpark.jpa.entity.member.Member;
import com.raonpark.jpa.entity.member.MemberType;
import com.raonpark.jpa.entity.member.Seller;
import com.raonpark.jpa.entity.product.Product;
import com.raonpark.jpa.repository.ProductRepository;
import com.raonpark.jpa.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean registerProduct(Member member, Product product) {
        if(member.getMemberType().equals(MemberType.SELLER)) {
            productRepository.save(product);
        }
        return false;
    }

    @Override
    public Product getProductByName(Seller seller, String name) {
        Optional<Product> product = productRepository.findByName(name);
        
        return product.get();
    }
    
}

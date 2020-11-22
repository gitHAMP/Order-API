package com.upc.orderapi.repository;


import com.upc.orderapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositiry extends JpaRepository<Product, Long> {

}
package com.example.quan_ly_kho.repository;

import com.example.quan_ly_kho.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
  List<Product> findByIdNotIn(List<Long> ids);
}

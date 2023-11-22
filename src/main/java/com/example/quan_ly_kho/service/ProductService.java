package com.example.quan_ly_kho.service;

import java.util.List;

import com.example.quan_ly_kho.dto.ProductDto;
import com.example.quan_ly_kho.dto.request.ProductRequest;

public interface ProductService {
    List<ProductDto> getProducts();
    List<ProductDto> getAllProduct(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductDto createProduct(ProductRequest productRequest);
    ProductDto getProductById(Long productId);
    ProductDto updateProduct(Long productId,ProductRequest productRequest);
    List<ProductDto> getProductsRemaining();
}

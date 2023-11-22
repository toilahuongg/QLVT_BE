package com.example.quan_ly_kho.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quan_ly_kho.dto.ProductDto;
import com.example.quan_ly_kho.dto.request.ProductRequest;
import com.example.quan_ly_kho.service.ProductService;
import com.example.quan_ly_kho.utils.AppConstants;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtos = productService.getProducts();
        return ResponseEntity.ok(productDtos);
    }


    @GetMapping("/remaining")
    public ResponseEntity<List<ProductDto>> getProductsRemaining() {
        List<ProductDto> productDtos = productService.getProductsRemaining();
         HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "customers 0-0/100");
        return new ResponseEntity<List<ProductDto>>(productDtos,
                headers,
                HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductsyPage(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "customers 0-0/100");
        return new ResponseEntity<List<ProductDto>>(productService.getAllProduct(pageNo, pageSize, sortBy, sortDir),
                headers,
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductRequest productRequest) {
        ProductDto productDto1 = productService.createProduct(productRequest);
        return ResponseEntity.ok(productDto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        ProductDto productDto = productService.getProductById(productId);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long productId,
            @RequestBody ProductRequest productRequest) {
        ProductDto productDto1 = productService.updateProduct(productId, productRequest);
        return ResponseEntity.ok(productDto1);
    }
}

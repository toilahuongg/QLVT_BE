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

import com.example.quan_ly_kho.dto.ProductBranchDto;
import com.example.quan_ly_kho.dto.request.ProductBranchRequest;
import com.example.quan_ly_kho.service.ProductBranchService;
import com.example.quan_ly_kho.utils.AppConstants;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products-branch")
public class ProductBranchController {
    private ProductBranchService productBranchService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductBranchDto>> getProductsBranch() {
        List<ProductBranchDto> dtoList = productBranchService.getProductBranches();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping
    public ResponseEntity<List<ProductBranchDto>> getAllProductBranch(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "customers 0-0/100");
        return new ResponseEntity<List<ProductBranchDto>>(
                productBranchService.getAllProductBranch(pageNo, pageSize, sortBy, sortDir),
                headers,
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductBranchDto> createProductBranch(@RequestBody ProductBranchRequest pbranchRequest) {

        ProductBranchDto result = productBranchService.createProductBranch(pbranchRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBranchDto> getPBranchById(@PathVariable("id") Long pBranchId) {
        ProductBranchDto result = productBranchService.getProductBranchById(pBranchId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductBranchDto> updatePBranch(@PathVariable("id") Long pBranchId,
            @RequestBody ProductBranchRequest productBranchRequest) {
        ProductBranchDto result = productBranchService.updateProductBranch(pBranchId, productBranchRequest);
        return ResponseEntity.ok(result);
    }
}

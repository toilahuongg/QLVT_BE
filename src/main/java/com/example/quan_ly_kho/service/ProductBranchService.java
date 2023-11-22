package com.example.quan_ly_kho.service;

import java.util.List;

import com.example.quan_ly_kho.dto.ProductBranchDto;
import com.example.quan_ly_kho.dto.request.ProductBranchRequest;

public interface ProductBranchService {
    List<ProductBranchDto> getProductBranches();
    List<ProductBranchDto> getAllProductBranch(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductBranchDto createProductBranch(ProductBranchRequest productbranchRequest);
    ProductBranchDto getProductBranchById(Long productbranchId);
    ProductBranchDto updateProductBranch(Long productbranchId,ProductBranchRequest productbranchDto);
}

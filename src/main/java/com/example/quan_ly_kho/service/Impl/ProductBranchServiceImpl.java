package com.example.quan_ly_kho.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.quan_ly_kho.dto.ProductBranchDto;
import com.example.quan_ly_kho.dto.request.ProductBranchRequest;
import com.example.quan_ly_kho.entity.Branch;
import com.example.quan_ly_kho.entity.Product;
import com.example.quan_ly_kho.entity.ProductBranch;
import com.example.quan_ly_kho.exception.ResourceNotFoundException;
import com.example.quan_ly_kho.repository.BranchRepository;
import com.example.quan_ly_kho.repository.ProductBranchRepository;
import com.example.quan_ly_kho.repository.ProductRepository;
import com.example.quan_ly_kho.service.ProductBranchService;
import com.example.quan_ly_kho.utils.AppConstants;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductBranchServiceImpl implements ProductBranchService {
        private BranchRepository branchRepository;
        private ProductRepository productRepository;
        private ProductBranchRepository productBranchRepository;
        private ModelMapper modelMapper;

        @Override
        public List<ProductBranchDto> getProductBranches() {
                List<ProductBranch> productsBranch = productBranchRepository.findAll();
                List<ProductBranchDto> productsBranchDtos = productsBranch.stream()
                                .map(branch -> modelMapper.map(branch, ProductBranchDto.class))
                                .collect(Collectors.toList());
                return productsBranchDtos;
        }

        @Override
        public List<ProductBranchDto> getAllProductBranch(int pageNo, int pageSize, String sortBy, String sortDir) {
                Branch branch = branchRepository.findById(AppConstants.DEFAULT_BRANCH_ID).orElseThrow(
                                () -> new ResourceNotFoundException("Branch", "id", AppConstants.DEFAULT_BRANCH_ID));
                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();
                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                Page<ProductBranch> productBranches = productBranchRepository
                                .findByBranch(branch, pageable);
                List<ProductBranch> listOfProductBranches = productBranches.getContent();
                List<ProductBranchDto> contents = listOfProductBranches.stream()
                                .map(pbranch -> modelMapper.map(pbranch, ProductBranchDto.class))
                                .collect(Collectors.toList());

                return contents;
        }

        @Override
        public ProductBranchDto createProductBranch(ProductBranchRequest productbranchRequest) {
                Branch branch = branchRepository.findById(AppConstants.DEFAULT_BRANCH_ID).orElseThrow(
                                () -> new ResourceNotFoundException("Branch", "id", AppConstants.DEFAULT_BRANCH_ID));
                Product product = productRepository.findById(productbranchRequest.getProductId()).orElseThrow(
                                () -> new ResourceNotFoundException("Product", "id",
                                                productbranchRequest.getProductId()));
                ProductBranch productBranch = new ProductBranch();
                productBranch.setBranch(branch);
                productBranch.setQuantity(productbranchRequest.getQuantity());
                productBranch.setProduct(product);
                ProductBranch saveBran = productBranchRepository.save(productBranch);
                ProductBranchDto result = modelMapper.map(saveBran, ProductBranchDto.class);
                return result;
        }

        @Override
        public ProductBranchDto getProductBranchById(Long pBranchId) {
                ProductBranch pbranch = productBranchRepository.findById(pBranchId).orElseThrow(
                                () -> new ResourceNotFoundException("ProductBranch", "id", pBranchId));
                ProductBranchDto pbranchDto = modelMapper.map(pbranch, ProductBranchDto.class);
                return pbranchDto;
        }

        @Override
        public ProductBranchDto updateProductBranch(Long pbranchId, ProductBranchRequest productbranchRequest) {
                ProductBranch pbranch = productBranchRepository.findById(pbranchId).orElseThrow(
                                () -> new ResourceNotFoundException("ProductBranch", "id", pbranchId));
                pbranch.setQuantity(pbranch.getQuantity());
                ProductBranch savePBranch = productBranchRepository.save(pbranch);
                return modelMapper.map(savePBranch, ProductBranchDto.class);
        }

}

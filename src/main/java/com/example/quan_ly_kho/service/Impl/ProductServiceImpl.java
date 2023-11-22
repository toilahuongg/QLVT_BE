package com.example.quan_ly_kho.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.quan_ly_kho.dto.ProductDto;
import com.example.quan_ly_kho.dto.request.ProductRequest;
import com.example.quan_ly_kho.entity.Branch;
import com.example.quan_ly_kho.entity.Product;
import com.example.quan_ly_kho.entity.ProductBranch;
import com.example.quan_ly_kho.exception.ResourceNotFoundException;
import com.example.quan_ly_kho.repository.BranchRepository;
import com.example.quan_ly_kho.repository.ProductBranchRepository;
import com.example.quan_ly_kho.repository.ProductRepository;
import com.example.quan_ly_kho.service.ProductService;
import com.example.quan_ly_kho.utils.AppConstants;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductBranchRepository productBranchRepository;
    private BranchRepository branchRepository;
    private ModelMapper modelMapper;

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map((product)->modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDto> getProductsRemaining() {
        Branch branch = branchRepository.findById(AppConstants.DEFAULT_BRANCH_ID).orElseThrow(
                ()->new ResourceNotFoundException("Branch","id",AppConstants.DEFAULT_BRANCH_ID)
        );
        List<ProductBranch> productsBranch = productBranchRepository.findByBranch(branch);
        List<Long> ids = productsBranch.stream().map((product) -> product.getProduct().getId()).collect(Collectors.toList());
        List<Product> products = productRepository.findByIdNotIn(ids);
        List<ProductDto> productsDto = products.stream().map((product)->modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return productsDto;
    }

    @Override
    public List<ProductDto> getAllProduct(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Product> products = productRepository.findAll(pageable);
        List<Product> listOfProducts = products.getContent();
        List<ProductDto> contents = listOfProducts.stream()
                .map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());

        return contents;
    }

    @Override
    public ProductDto createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setQuantity(productRequest.getQuantity());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        Product savePro = productRepository.save(product);
        ProductDto productDto1 = modelMapper.map(savePro, ProductDto.class);
        return productDto1;
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()->new ResourceNotFoundException("Product","id",productId)
        );
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()->new ResourceNotFoundException("Product","id",productId)
        );

        product.setName(productRequest.getName());
        product.setQuantity(productRequest.getQuantity());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        Product savePro = productRepository.save(product);
        return modelMapper.map(savePro, ProductDto.class);
    }
}

package com.example.quan_ly_kho.service;

import com.example.quan_ly_kho.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomer(int pageNo, int pageSize, String sortBy, String sortDir);
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto getCustomerById(Long customerId);
    CustomerDto updateCustomer(Long customerId,CustomerDto customerDto);
    List<CustomerDto> getCustomerss();
}

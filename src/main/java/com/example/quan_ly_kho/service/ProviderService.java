package com.example.quan_ly_kho.service;

import java.util.List;

import com.example.quan_ly_kho.dto.ProviderDto;
import com.example.quan_ly_kho.dto.ResultResponse;

public interface ProviderService {
    List<ProviderDto> getProviders();
    ResultResponse getAllProvider(int pageNo, int pageSize, String sortBy, String sortDir);
    ProviderDto createProvider(ProviderDto providerDto);
    ProviderDto getProviderById(Long providerId);
    ProviderDto updateProvider(Long providerId,ProviderDto providerDto);
}

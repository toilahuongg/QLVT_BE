package com.example.quan_ly_kho.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quan_ly_kho.dto.ProviderDto;
import com.example.quan_ly_kho.dto.ResultResponse;
import com.example.quan_ly_kho.service.ProviderService;
import com.example.quan_ly_kho.utils.AppConstants;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/provider")
public class ProviderController {
    private ProviderService providerService;

    @GetMapping("/all")
    public ResponseEntity<List<ProviderDto>> getProviders() {
        List<ProviderDto> providerDtos = providerService.getProviders();
        return ResponseEntity.ok(providerDtos);
    }

    @GetMapping
    public ResponseEntity<ResultResponse> getAllProviders(
            @RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        ResultResponse rs = providerService.getAllProvider(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(rs);
    }

    @PostMapping
    public ResponseEntity<ProviderDto> createProvider(@RequestBody ProviderDto providerDto){
        ProviderDto providerDto1 = providerService.createProvider(providerDto);
        return ResponseEntity.ok(providerDto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDto> getProviderById(@PathVariable("id") Long providerId){
        ProviderDto providerDto = providerService.getProviderById(providerId);
        return ResponseEntity.ok(providerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDto> updateProvider(@PathVariable("id") Long providerId,
                                                   @RequestBody ProviderDto providerDto){
        ProviderDto providerDto1 = providerService.updateProvider(providerId,providerDto);
        return ResponseEntity.ok(providerDto1);
    }
}

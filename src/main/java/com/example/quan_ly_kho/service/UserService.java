package com.example.quan_ly_kho.service;

import java.util.List;

import com.example.quan_ly_kho.dto.ResultResponse;
import com.example.quan_ly_kho.dto.UserDto;
import com.example.quan_ly_kho.dto.request.LoginRequest;
import com.example.quan_ly_kho.dto.request.RegisterRequest;
import com.example.quan_ly_kho.dto.request.UserRequest;

public interface UserService {
    String login(LoginRequest loginRequest);
    String register(RegisterRequest registerRequest);
    List<UserDto> getAllUser(int pageNo, int pageSize, String sortBy, String sortDir);
    UserDto createUser(UserRequest userRequest);
    UserDto editUser(Long id,UserRequest userRequest);
    Boolean deleteUser(Long id);
    UserDto currentUser(String username);
    UserDto getUserById(Long id);
    ResultResponse getUsersByBranch(Long branchId,int pageNo, int pageSize, String sortBy, String sortDir);
}

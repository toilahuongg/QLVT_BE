package com.example.quan_ly_kho.controller;

import com.example.quan_ly_kho.dto.ResultResponse;
import com.example.quan_ly_kho.dto.UserDto;
import com.example.quan_ly_kho.dto.request.UserRequest;
import com.example.quan_ly_kho.service.UserService;
import com.example.quan_ly_kho.utils.AppConstants;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsersByPage(
            @RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "users 0-9/100");
        return new ResponseEntity<List<UserDto>>(userService.getAllUser(pageNo, pageSize, sortBy, sortDir), headers,
                HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest userRequest){
        UserDto userDto = userService.createUser(userRequest);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable("id") Long userId,
                                            @RequestBody UserRequest userRequest){
        UserDto userDto = userService.editUser(userId,userRequest);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        Map<String, String> data = new HashMap<>();
        data.put("ok", "true");
        return ResponseEntity.ok(data);
    }

    @GetMapping("/users/current")
    public ResponseEntity<UserDto> activeUser(HttpServletRequest request){
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(userService.currentUser(username));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserByid(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/users/branch/{branchId}")
    public ResponseEntity<ResultResponse> getAllUsersByBranch(
            @PathVariable("branchId") Long id,
            @RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return ResponseEntity.ok(userService.getUsersByBranch(id,pageNo, pageSize, sortBy, sortDir));
    }
}

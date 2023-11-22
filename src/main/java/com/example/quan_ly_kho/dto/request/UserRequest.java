package com.example.quan_ly_kho.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String role;
    private Date bornDate;
    private Boolean status;
}

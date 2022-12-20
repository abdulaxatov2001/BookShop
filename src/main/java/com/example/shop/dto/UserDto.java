package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String userName;
    private String password;
    private String pre_password;
    private String fullName;

    public UserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}

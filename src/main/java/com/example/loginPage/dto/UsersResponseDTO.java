package com.example.loginPage.dto;

import com.example.loginPage.model.Users;

public record UsersResponseDTO(String email, String password) {

    public UsersResponseDTO(Users users){
        this(users.getEmail(), users.getPassword());
    }
}

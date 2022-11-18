package com.example.demo.src.token.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostTokenReq {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}

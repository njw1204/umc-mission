package com.example.demo.src.user.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PostUserReq {
    @Pattern(regexp = "\\w{1,100}")
    @NotNull
    private String username;
    @Pattern(regexp = "\\S{1,100}")
    @NotNull
    private String password;
}

package com.example.demo.src.user.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PatchUserReq {
    @Pattern(regexp = "\\S{1,100}")
    @NotNull
    private String oldPassword;
    @Pattern(regexp = "\\S{1,100}")
    private String password;
}

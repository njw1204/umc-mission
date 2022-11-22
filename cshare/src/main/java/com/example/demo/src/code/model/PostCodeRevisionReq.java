package com.example.demo.src.code.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostCodeRevisionReq {
    @Size(max = 10_000_000)
    @NotNull
    private String content;
}

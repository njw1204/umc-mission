package com.example.demo.src.code.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostCodeReq {
    @Size(max = 300)
    @NotBlank
    private String name;
    @Size(max = 5000)
    @NotNull
    private String description;
    @Size(max = 10_000_000)
    @NotNull
    private String content;
    @NotNull
    private CodeVisibility visibility;
}

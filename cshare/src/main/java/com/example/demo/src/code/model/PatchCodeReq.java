package com.example.demo.src.code.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PatchCodeReq {
    @Size(max = 300)
    private String name;
    @Size(max = 5000)
    private String description;
    private CodeVisibility visibility;
}

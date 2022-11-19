package com.example.demo.src.code.model;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class GetCodesReq {
    private boolean me;
    @Positive
    private int page;
    @Positive
    private int limit;
}

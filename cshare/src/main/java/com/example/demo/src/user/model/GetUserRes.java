package com.example.demo.src.user.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetUserRes {
    private Long userId;
    private String username;
    private LocalDateTime registerDateTime;
}

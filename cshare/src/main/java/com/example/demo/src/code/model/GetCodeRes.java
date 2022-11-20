package com.example.demo.src.code.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetCodeRes {
    private Long codeId;
    private Long userId;
    private String username;
    private String name;
    private String description;
    private String content;
    private CodeVisibility visibility;
    private LocalDateTime registerDateTime;
    private LocalDateTime updateDateTime;
}

package com.example.demo.src.board.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetArticleRes {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime registerDateTime;
}

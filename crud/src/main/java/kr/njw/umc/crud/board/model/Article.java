package kr.njw.umc.crud.board.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    private Long id;
    private String title = "";
    private String description = "";
    private LocalDateTime registerDateTime = LocalDateTime.MIN;
}

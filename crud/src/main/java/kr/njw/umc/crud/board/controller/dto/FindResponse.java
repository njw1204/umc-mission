package kr.njw.umc.crud.board.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime registerDateTime;
}

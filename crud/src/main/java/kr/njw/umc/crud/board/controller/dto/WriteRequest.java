package kr.njw.umc.crud.board.controller.dto;

import lombok.Data;

@Data
public class WriteRequest {
    private String title;
    private String description;
}

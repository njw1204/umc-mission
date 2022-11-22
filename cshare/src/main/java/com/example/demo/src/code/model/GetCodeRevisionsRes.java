package com.example.demo.src.code.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class GetCodeRevisionsRes {
    List<RevisionItem> items = new ArrayList<>();

    @Data
    public static class RevisionItem {
        private Long revisionId;
        private String content;
        private LocalDateTime registerDateTime;
    }
}

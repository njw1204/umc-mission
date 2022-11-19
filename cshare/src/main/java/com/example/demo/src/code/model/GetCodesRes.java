package com.example.demo.src.code.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class GetCodesRes {
    List<CodeItem> items = new ArrayList<>();
    int page;
    int pageSize;
    int pageSizeLimit;
    long totalSize;

    @Data
    public static class CodeItem {
        private Long id;
        private Long userId;
        private String username;
        private String name;
        private String description;
        private CodeVisibility visibility;
        private LocalDateTime registerDateTime;
    }
}

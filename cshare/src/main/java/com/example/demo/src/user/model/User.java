package com.example.demo.src.user.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class User {
    private Long id;
    private String username = "";
    private String password = "";
    private LocalDateTime registerDateTime = LocalDateTime.MIN;
}

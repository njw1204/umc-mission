package com.example.demo.src.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cshare_user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String username = "";
    @Column(length = 100, nullable = false)
    private String password = "";
    @Column(nullable = false)
    private LocalDateTime registerDateTime = LocalDateTime.MIN;
}

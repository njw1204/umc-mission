package com.example.demo.src.code.model;

import com.example.demo.src.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cshare_code")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fork_from_id")
    private Code forkFrom;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length = 300, nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private CodeVisibility visibility;
    @Column(nullable = false)
    private LocalDateTime registerDateTime = LocalDateTime.MIN;
}

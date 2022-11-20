package com.example.demo.src.code.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cshare_code_revision")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class CodeRevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "code_id", nullable = false)
    private Code code;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDateTime registerDateTime;
}

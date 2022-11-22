package com.example.demo.src.code;

import com.example.demo.src.code.model.CodeRevision;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRevisionRepository extends JpaRepository<CodeRevision, Long> {
    List<CodeRevision> findAllByCodeIdOrderByIdDesc(Long codeId);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"code.user"})
    Optional<CodeRevision> findFirstByCodeIdOrderByIdDesc(Long codeId);
}

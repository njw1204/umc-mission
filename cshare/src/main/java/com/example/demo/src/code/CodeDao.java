package com.example.demo.src.code;

import com.example.demo.src.code.model.Code;
import com.example.demo.src.code.model.CodeVisibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeDao extends JpaRepository<Code, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"forkFrom", "user"})
    Page<Code> findAllByVisibilityOrderByIdDesc(CodeVisibility visibility, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"forkFrom", "user"})
    Page<Code> findAllByUserIdOrderByIdDesc(Long userId, Pageable pageable);
}

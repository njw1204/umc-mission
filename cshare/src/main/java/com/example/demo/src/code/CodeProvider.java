package com.example.demo.src.code;

import com.example.demo.src.code.model.Code;
import com.example.demo.src.code.model.CodeVisibility;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodeProvider {
    private final CodeDao codeDao;

    public Page<Code> findPublicCodes(int page, int limit) {
        return this.codeDao.findAllByVisibilityOrderByIdDesc(CodeVisibility.PUBLIC, PageRequest.of(page, limit));
    }

    public Page<Code> findMyCodes(Long userId, int page, int limit) {
        return this.codeDao.findAllByUserIdOrderByIdDesc(userId, PageRequest.of(page, limit));
    }
}

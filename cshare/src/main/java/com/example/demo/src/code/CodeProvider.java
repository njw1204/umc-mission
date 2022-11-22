package com.example.demo.src.code;

import com.example.demo.src.code.model.Code;
import com.example.demo.src.code.model.CodeRevision;
import com.example.demo.src.code.model.CodeVisibility;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CodeProvider {
    private final CodeRepository codeRepository;
    private final CodeRevisionRepository codeRevisionRepository;

    public Page<Code> findPublicCodes(int page, int limit) {
        return this.codeRepository.findAllByVisibilityOrderByIdDesc(CodeVisibility.PUBLIC, PageRequest.of(page, limit));
    }

    public Page<Code> findMyCodes(Long userId, int page, int limit) {
        return this.codeRepository.findAllByUserIdOrderByIdDesc(userId, PageRequest.of(page, limit));
    }

    public Optional<CodeRevision> findLatestCodeRevision(Long userId, Long codeId) {
        CodeRevision codeRevision = this.codeRevisionRepository.findFirstByCodeIdOrderByIdDesc(codeId).orElse(null);

        if (codeRevision == null) {
            return Optional.empty();
        }

        if (codeRevision.getCode().getVisibility() == CodeVisibility.PUBLIC ||
                codeRevision.getCode().getUser().getId().equals(userId)) {
            return Optional.of(codeRevision);
        }

        return Optional.empty();
    }

    public List<CodeRevision> findCodeRevisions(Long userId, Long codeId) {
        List<CodeRevision> codeRevisions = this.codeRevisionRepository.findAllByCodeIdOrderByIdDesc(codeId);

        if (codeRevisions.isEmpty()) {
            return new ArrayList<>();
        }

        if (codeRevisions.get(0).getCode().getVisibility() == CodeVisibility.PUBLIC ||
                codeRevisions.get(0).getCode().getUser().getId().equals(userId)) {
            return codeRevisions;
        }

        return new ArrayList<>();
    }
}

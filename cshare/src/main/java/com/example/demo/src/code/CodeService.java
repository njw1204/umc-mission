package com.example.demo.src.code;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.code.model.Code;
import com.example.demo.src.code.model.CodeRevision;
import com.example.demo.src.code.model.CodeVisibility;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class CodeService {
    private final CodeRepository codeRepository;
    private final CodeRevisionRepository codeRevisionRepository;
    private final UserDao userDao;

    public Long createCode(Long userId, String name, String description, String content, CodeVisibility visibility)
            throws BaseException {
        try {
            User user = this.userDao.findUser(userId).orElse(null);

            if (user == null) {
                throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
            }

            Code code = Code.builder()
                    .user(user)
                    .name(name)
                    .description(description)
                    .visibility(visibility)
                    .registerDateTime(LocalDateTime.now())
                    .build();
            Long codeId = this.codeRepository.saveAndFlush(code).getId();
            CodeRevision codeRevision = CodeRevision.builder()
                    .code(code)
                    .content(content)
                    .registerDateTime(code.getRegisterDateTime())
                    .build();
            this.codeRevisionRepository.save(codeRevision);
            return codeId;
        } catch (DataAccessException e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void updateCode(Long userId, Long codeId, String name, String description, CodeVisibility visibility)
            throws BaseException {
        try {
            Code code = this.codeRepository.findById(codeId).orElse(null);

            if (code == null) {
                throw new BaseException(BaseResponseStatus.RESPONSE_ERROR);
            }

            if (!code.getUser().getId().equals(userId)) {
                throw new BaseException(BaseResponseStatus.INVALID_USER_JWT);
            }

            if (!StringUtils.defaultString(name).isBlank()) {
                code.updateName(name);
            }

            if (description != null) {
                code.updateDescription(description);
            }

            if (visibility != null) {
                code.updateVisibility(visibility);
            }

            this.codeRepository.save(code);
        } catch (DataAccessException e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void deleteCode(Long userId, Long codeId) throws BaseException {
        try {
            Code code = this.codeRepository.findById(codeId).orElse(null);

            if (code == null) {
                return;
            }

            if (!code.getUser().getId().equals(userId)) {
                throw new BaseException(BaseResponseStatus.INVALID_USER_JWT);
            }

            this.codeRepository.delete(code);
        } catch (DataAccessException e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

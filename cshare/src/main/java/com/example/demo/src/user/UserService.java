package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public Long createUser(String username, String password) throws BaseException {
        try {
            return this.userDao.createUser(User.builder()
                    .username(username)
                    .password(this.passwordEncoder.encode(password))
                    .registerDateTime(LocalDateTime.now())
                    .build()).orElse(null);
        } catch (DataAccessException e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void updateUser(Long userId, String oldPassword, String password) throws BaseException {
        try {
            User user = this.userDao.findUser(userId).orElse(null);

            if (user == null || !this.passwordEncoder.matches(oldPassword, user.getPassword())) {
                throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
            }

            if (password != null) {
                this.userDao.updateUserPassword(userId, this.passwordEncoder.encode(password));
            }
        } catch (DataAccessException e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void deleteUser(String username, String password) throws BaseException {
        try {
            User user = this.userDao.findUserByUsername(username).orElse(null);

            if (user == null || !this.passwordEncoder.matches(password, user.getPassword())) {
                throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
            }

            this.userDao.deleteUser(user.getId());
        } catch (DataAccessException e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

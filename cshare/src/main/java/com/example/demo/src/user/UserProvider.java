package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserProvider {
    private final UserDao userDao;

    public Optional<User> findUser(Long userId) throws BaseException {
        try {
            return this.userDao.findUser(userId);
        } catch (DataAccessException e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

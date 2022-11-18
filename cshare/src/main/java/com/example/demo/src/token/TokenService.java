package com.example.demo.src.token;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.User;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final JwtService jwtService;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public String createAccessToken(String username, String password) throws BaseException {
        try {
            User user = this.userDao.findUserByUsername(username).orElse(null);

            if (user == null || !this.passwordEncoder.matches(password, user.getPassword())) {
                throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
            }

            return this.jwtService.createToken(user.getId().toString());
        } catch (DataAccessException e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

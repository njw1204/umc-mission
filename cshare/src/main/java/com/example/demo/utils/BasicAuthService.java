package com.example.demo.utils;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

@Service
public class BasicAuthService {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_PREFIX = "Basic ";

    public String parseEncodedToken(String basicHeader) {
        String headerTrimmed = Objects.requireNonNullElse(basicHeader, "").trim();

        if (StringUtils.startsWithIgnoreCase(headerTrimmed, BASIC_PREFIX)) {
            return StringUtils.substringAfter(headerTrimmed, BASIC_PREFIX);
        }

        return "";
    }

    public Optional<BasicAuthToken> decodeToken(String encodedToken) {
        try {
            String usernameAndPassword = new String(Base64.getDecoder().decode(encodedToken));
            String[] split = StringUtils.split(usernameAndPassword, ":", 2);

            if (split.length < 2) {
                return Optional.empty();
            }

            BasicAuthToken token = new BasicAuthToken();
            token.setUsername(split[0]);
            token.setPassword(split[1]);
            return Optional.of(token);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Data
    public static class BasicAuthToken {
        private String username;
        private String password;

        public String toString() {
            return Base64.getEncoder().encodeToString((this.username + ":" + this.password).getBytes());
        }
    }
}

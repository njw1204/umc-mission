package com.example.demo.utils;

import com.example.demo.config.Constant;
import com.example.demo.config.secret.Secret;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService {
    public static final long TOKEN_VALID_TIME_MS = 1000L * 60 * 60 * 24 * 365;
    private static final String AUTHORITIES_KEY = "authorities";
    private static final String ENCODED_SECRET = Base64.getEncoder().encodeToString(Secret.JWT_SECRET_KEY.getBytes());

    public String createToken(String id) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(id)
                .claim(AUTHORITIES_KEY, List.of(Constant.ROLE_USER))
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALID_TIME_MS))
                .signWith(SignatureAlgorithm.HS256, ENCODED_SECRET)
                .compact();
    }

    public Optional<Authentication> getAuthentication(String token) {
        if (!this.validateToken(token)) {
            return Optional.empty();
        }

        Claims claims = this.getClaims(token);

        if (StringUtils.isBlank(claims.getSubject())) {
            return Optional.empty();
        }

        List<? extends GrantedAuthority> authorities = this.getAuthorities(claims);
        UserDetails userDetails = new User(claims.getSubject(), "", authorities);

        return Optional.of(new UsernamePasswordAuthenticationToken(userDetails, "", authorities));
    }

    private boolean validateToken(String token) {
        try {
            Claims claims = this.getClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(ENCODED_SECRET).parseClaimsJws(token).getBody();
    }

    private List<? extends GrantedAuthority> getAuthorities(Claims claims) {
        Object authorities = claims.get(AUTHORITIES_KEY);

        if (authorities instanceof Collection<?> authorityCollection) {
            return authorityCollection.stream()
                    .map(Object::toString)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}

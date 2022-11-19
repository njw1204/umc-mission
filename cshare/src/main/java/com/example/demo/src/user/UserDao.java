package com.example.demo.src.user;

import com.example.demo.src.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);

    public Optional<Long> createUser(User user) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("username", user.getUsername());
        parameters.addValue("password", user.getPassword());
        parameters.addValue("register_date_time", user.getRegisterDateTime());

        this.jdbcTemplate.update("""
                INSERT INTO
                    cshare_user (
                        username, password, register_date_time
                    )
                VALUE
                    (
                        :username, :password, :register_date_time
                    )""", parameters, generatedKeyHolder);

        return Optional.of(Objects.requireNonNull(generatedKeyHolder.getKey()).longValue());
    }

    public void updateUserPassword(Long userId, String password) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", userId);
        parameters.addValue("password", password);

        this.jdbcTemplate.update("""
                UPDATE
                    cshare_user
                SET
                    password = :password
                WHERE
                    id = :id""", parameters);
    }

    public void deleteUser(Long userId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", userId);

        this.jdbcTemplate.update("""
                DELETE FROM
                    cshare_user
                WHERE
                    id = :id""", parameters);
    }

    public Optional<User> findUser(Long userId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", userId);

        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject("""
                    SELECT * FROM cshare_user WHERE id = :id""", parameters, this.userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<User> findUserByUsername(String username) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("username", username);

        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject("""
                    SELECT * FROM cshare_user WHERE username = :username""", parameters, this.userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

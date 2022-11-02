package kr.njw.umc.crud.board.dao;

import kr.njw.umc.crud.board.model.Article;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardDaoImpl implements BoardDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Article> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM board", new BeanPropertyRowMapper<>(Article.class));
    }

    public Optional<Article> find(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject("SELECT * FROM board WHERE id = :id",
                    parameters, new BeanPropertyRowMapper<>(Article.class)));
        } catch (EmptyResultDataAccessException e) {
            this.logger.warn(e.toString());
            return Optional.empty();
        } catch (DataAccessException e) {
            this.logger.error(e.toString());
            return Optional.empty();
        }
    }

    public Long save(Article article) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", article.getId());
        parameters.addValue("title", article.getTitle());
        parameters.addValue("description", article.getDescription());
        parameters.addValue("register_date_time", article.getRegisterDateTime());

        try {
            this.jdbcTemplate.update("""
                    INSERT INTO
                        board (
                            id, title, description, register_date_time
                        )
                    VALUE
                        (
                            :id, :title, :description, :register_date_time
                        )
                    ON DUPLICATE KEY UPDATE
                        title = :title,
                        description = :description,
                        register_date_time = :register_date_time""", parameters, generatedKeyHolder);
        } catch (DataAccessException e) {
            this.logger.error(e.toString());
            return null;
        }

        if (article.getId() != null) {
            return article.getId();
        }

        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }

    public boolean delete(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        try {
            this.jdbcTemplate.update("DELETE FROM board WHERE id = :id", parameters);
        } catch (DataAccessException e) {
            this.logger.error(e.toString());
            return false;
        }

        return true;
    }
}

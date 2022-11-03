package kr.njw.umc.crud.board.dao;

import kr.njw.umc.crud.board.model.Article;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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
    private final RowMapper<Article> articleRowMapper = BeanPropertyRowMapper.newInstance(Article.class);
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Article> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM board", this.articleRowMapper);
    }

    public Optional<Article> find(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject("SELECT * FROM board WHERE id = :id",
                    parameters, this.articleRowMapper));
        } catch (EmptyResultDataAccessException e) {
            this.logger.warn(e.toString());
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

        if (article.getId() != null) {
            return article.getId();
        }

        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }

    public boolean delete(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        this.jdbcTemplate.update("DELETE FROM board WHERE id = :id", parameters);

        return true;
    }
}

package kr.njw.umc.crud.board.dao;

import kr.njw.umc.crud.board.model.Article;

import java.util.List;
import java.util.Optional;

public interface BoardDao {
    List<Article> findAll();
    Optional<Article> find(Long id);
    Long save(Article article);
    boolean delete(Long id);
}

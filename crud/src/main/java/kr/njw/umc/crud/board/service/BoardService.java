package kr.njw.umc.crud.board.service;

import kr.njw.umc.crud.board.model.Article;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<Article> findAll();
    Optional<Article> find(Long id);
    Long write(String title, String description);
    Long edit(Long id, String title, String description);
    boolean delete(Long id);
}

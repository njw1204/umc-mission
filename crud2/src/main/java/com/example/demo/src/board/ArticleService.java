package com.example.demo.src.board;

import com.example.demo.src.board.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleDao boardDao;

    public Long createArticle(String title, String description) {
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setRegisterDateTime(LocalDateTime.now());
        return this.boardDao.saveArticle(article);
    }

    public Long updateArticle(Long id, String title, String description) {
        Article article = this.boardDao.findArticle(id).orElse(null);

        if (article == null) {
            return null;
        }

        article.setTitle(title);
        article.setDescription(description);
        return this.boardDao.saveArticle(article);
    }

    public boolean deleteArticle(Long id) {
        return this.boardDao.deleteArticle(id);
    }
}

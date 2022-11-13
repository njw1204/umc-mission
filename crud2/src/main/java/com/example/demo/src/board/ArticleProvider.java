package com.example.demo.src.board;

import com.example.demo.src.board.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleProvider {
    private final ArticleDao articleDao;

    public List<Article> findArticles() {
        return this.articleDao.findArticles();
    }

    public Optional<Article> findArticle(Long id) {
        return this.articleDao.findArticle(id);
    }
}

package kr.njw.umc.crud.board.service;

import kr.njw.umc.crud.board.dao.BoardDao;
import kr.njw.umc.crud.board.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;

    public List<Article> findAll() {
        return this.boardDao.findAll();
    }

    public Optional<Article> find(Long id) {
        return this.boardDao.find(id);
    }

    public Long write(String title, String description) {
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setRegisterDateTime(LocalDateTime.now());

        return this.boardDao.save(article);
    }

    public Long edit(Long id, String title, String description) {
        Article article = this.boardDao.find(id).orElse(null);

        if (article == null) {
            return null;
        }

        article.setTitle(title);
        article.setDescription(description);

        return this.boardDao.save(article);
    }

    public boolean delete(Long id) {
        return this.boardDao.delete(id);
    }
}

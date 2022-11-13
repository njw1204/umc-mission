package com.example.demo.src.board;

import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.board.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/articles")
@RestController
public class ArticleController {
    private final ArticleProvider articleProvider;
    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<List<GetArticleRes>>> getArticles() {
        return ResponseEntity.ok(new BaseResponse<>(this.articleProvider.findArticles().stream()
                .map(article -> {
                    GetArticleRes getArticleRes = new GetArticleRes();
                    getArticleRes.setId(article.getId());
                    getArticleRes.setTitle(article.getTitle());
                    getArticleRes.setDescription(article.getDescription());
                    getArticleRes.setRegisterDateTime(article.getRegisterDateTime());
                    return getArticleRes;
                }).toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<GetArticleRes>> getArticle(@PathVariable("id") Long id) {
        GetArticleRes getArticleRes = new GetArticleRes();
        Article article = this.articleProvider.findArticle(id).orElse(null);

        if (article == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR));
        }

        getArticleRes.setId(article.getId());
        getArticleRes.setTitle(article.getTitle());
        getArticleRes.setDescription(article.getDescription());
        getArticleRes.setRegisterDateTime(article.getRegisterDateTime());
        return ResponseEntity.ok(new BaseResponse<>(getArticleRes));
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<PostArticleRes>> postArticle(@RequestBody PostArticleReq request) {
        PostArticleRes postArticleRes = new PostArticleRes();
        postArticleRes.setId(this.articleService.createArticle(request.getTitle(), request.getDescription()));

        if (postArticleRes.getId() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse<>(BaseResponseStatus.DATABASE_ERROR));
        }

        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(postArticleRes.getId())
                        .toUri())
                .body(new BaseResponse<>(postArticleRes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<PostArticleRes>> putArticle(@PathVariable("id") Long id, @RequestBody PostArticleReq request) {
        PostArticleRes postArticleRes = new PostArticleRes();
        postArticleRes.setId(this.articleService.updateArticle(id, request.getTitle(), request.getDescription()));

        if (postArticleRes.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR));
        }

        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                .body(new BaseResponse<>(postArticleRes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") Long id) {
        this.articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}

package kr.njw.umc.crud.board.controller;

import kr.njw.umc.crud.board.controller.dto.DeleteResponse;
import kr.njw.umc.crud.board.controller.dto.FindResponse;
import kr.njw.umc.crud.board.controller.dto.WriteRequest;
import kr.njw.umc.crud.board.controller.dto.WriteResponse;
import kr.njw.umc.crud.board.model.Article;
import kr.njw.umc.crud.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("")
    public List<FindResponse> findAll() {
        return this.boardService.findAll().stream()
                .map(article -> {
                    FindResponse findResponse = new FindResponse();
                    findResponse.setId(article.getId());
                    findResponse.setTitle(article.getTitle());
                    findResponse.setDescription(article.getDescription());
                    findResponse.setRegisterDateTime(article.getRegisterDateTime());
                    return findResponse;
                }).toList();
    }

    @GetMapping("/{id}")
    public FindResponse find(@PathVariable("id") Long id) {
        FindResponse findResponse = new FindResponse();
        Article article = this.boardService.find(id).orElse(null);

        if (article != null) {
            findResponse.setId(article.getId());
            findResponse.setTitle(article.getTitle());
            findResponse.setDescription(article.getDescription());
            findResponse.setRegisterDateTime(article.getRegisterDateTime());
        }

        return findResponse;
    }

    @PostMapping("")
    public WriteResponse write(@RequestBody WriteRequest request) {
        WriteResponse writeResponse = new WriteResponse();
        writeResponse.setId(this.boardService.write(request.getTitle(), request.getDescription()));

        return writeResponse;
    }

    @PutMapping("/{id}")
    public WriteResponse edit(@PathVariable("id") Long id, @RequestBody WriteRequest request) {
        WriteResponse writeResponse = new WriteResponse();
        writeResponse.setId(this.boardService.edit(id, request.getTitle(), request.getDescription()));

        return writeResponse;
    }

    @DeleteMapping("/{id}")
    public DeleteResponse delete(@PathVariable("id") Long id) {
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setResult(this.boardService.delete(id));

        return deleteResponse;
    }
}

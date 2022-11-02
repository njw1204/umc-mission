package kr.njw.umc.crud.board.controller;

import kr.njw.umc.crud.board.controller.dto.DeleteResponse;
import kr.njw.umc.crud.board.controller.dto.FindResponse;
import kr.njw.umc.crud.board.controller.dto.WriteRequest;
import kr.njw.umc.crud.board.controller.dto.WriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    @GetMapping("")
    public List<FindResponse> findAll() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public FindResponse find(@PathVariable("id") Long id) {
        return new FindResponse();
    }

    @PostMapping("")
    public WriteResponse write(@RequestBody WriteRequest request) {
        return new WriteResponse();
    }

    @PutMapping("/{id}")
    public WriteResponse edit(@PathVariable("id") Long id, @RequestBody WriteRequest request) {
        return new WriteResponse();
    }

    @DeleteMapping("/{id}")
    public DeleteResponse delete(@PathVariable("id") Long id) {
        return new DeleteResponse();
    }
}

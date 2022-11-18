package com.example.demo.src.token;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.token.model.PostTokenReq;
import com.example.demo.src.token.model.PostTokenRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/tokens")
@RestController
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("")
    public ResponseEntity<BaseResponse<PostTokenRes>> postToken(@Valid @RequestBody PostTokenReq request)
            throws BaseException {
        PostTokenRes response = new PostTokenRes();
        response.setAccessToken(this.tokenService.createAccessToken(request.getUsername(), request.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(response));
    }
}

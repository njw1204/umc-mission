package com.example.demo.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpErrorController implements ErrorController {
    @RequestMapping("/error")
    public BaseResponse<?> error() {
        return new BaseResponse<>(BaseResponseStatus.SERVER_ERROR);
    }
}

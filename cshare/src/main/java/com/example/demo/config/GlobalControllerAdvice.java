package com.example.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<BaseResponse<?>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResponse<?>> handleBindException(BindException e) {
        return ResponseEntity.badRequest().body(new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResponse<?>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body(new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<?>> handleBaseException(BaseException e) {
        HttpStatus status = switch (e.getStatus()) {
            case REQUEST_ERROR -> HttpStatus.BAD_REQUEST;
            case EMPTY_JWT, INVALID_JWT, FAILED_TO_LOGIN -> HttpStatus.UNAUTHORIZED;
            case INVALID_USER_JWT -> HttpStatus.FORBIDDEN;
            case DUPLICATED_EMAIL -> HttpStatus.CONFLICT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        return ResponseEntity.status(status).body(new BaseResponse<>(e.getStatus()));
    }
}

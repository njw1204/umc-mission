package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.BasicAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserProvider userProvider;
    private final UserService userService;
    private final BasicAuthService basicAuthService;

    @PostMapping("")
    public ResponseEntity<BaseResponse<PostUserRes>> postUser(@Valid @RequestBody PostUserReq request)
            throws BaseException {
        PostUserRes response = new PostUserRes();
        response.setUserId(this.userService.createUser(request.getUsername(), request.getPassword()));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{userId}")
                        .buildAndExpand(response.getUserId())
                        .toUri())
                .body(new BaseResponse<>(response));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse<GetUserRes>> getUser(@PathVariable Long userId) throws BaseException {
        GetUserRes response = new GetUserRes();
        User user = this.userProvider.findUser(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(BaseResponseStatus.RESPONSE_ERROR));
        }

        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setRegisterDateTime(user.getRegisterDateTime());
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<GetUserRes>> getUserMe(Principal principal) throws BaseException {
        GetUserRes response = new GetUserRes();
        long userId;

        try {
            userId = Long.parseLong(principal != null ? principal.getName() : "");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(BaseResponseStatus.RESPONSE_ERROR));
        }

        User user = this.userProvider.findUser(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(BaseResponseStatus.RESPONSE_ERROR));
        }

        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setRegisterDateTime(user.getRegisterDateTime());
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PatchMapping("/me")
    public ResponseEntity<BaseResponse<PatchUserRes>> patchUserMe(@Valid @RequestBody PatchUserReq request,
                                                                  Principal principal) throws BaseException {
        PatchUserRes response = new PatchUserRes();
        long userId = Long.parseLong(principal.getName());
        this.userService.updateUser(userId, request.getOldPassword(), request.getPassword());
        response.setUserId(userId);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("/me")
    public ResponseEntity<BaseResponse<?>> deleteUserMe(@RequestHeader(defaultValue = "") String authorization)
            throws BaseException {
        BasicAuthService.BasicAuthToken token =
                this.basicAuthService.decodeToken(this.basicAuthService.parseEncodedToken(authorization)).orElse(null);

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new BaseResponse<>(BaseResponseStatus.FAILED_TO_LOGIN));
        }

        this.userService.deleteUser(token.getUsername(), token.getPassword());
        return ResponseEntity.ok(new BaseResponse<>(BaseResponseStatus.SUCCESS));
    }
}

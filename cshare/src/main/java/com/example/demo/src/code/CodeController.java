package com.example.demo.src.code;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.code.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/codes")
@RestController
public class CodeController {
    private final CodeProvider codeProvider;
    private final CodeService codeService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<GetCodesRes>> getCodes(@Valid GetCodesReq req, Principal principal) {
        GetCodesRes res = new GetCodesRes();
        Page<Code> codePage;

        if (req.isMe()) {
            if (principal == null) {
                res.setPage(res.getPage());
                res.setPageSize(0);
                res.setPageSizeLimit(req.getLimit());
                res.setTotalSize(0);
                return ResponseEntity.ok(new BaseResponse<>(res));
            }

            codePage =
                    this.codeProvider.findMyCodes(Long.valueOf(principal.getName()), req.getPage() - 1, req.getLimit());
        } else {
            codePage = this.codeProvider.findPublicCodes(req.getPage() - 1, req.getLimit());
        }

        res.setItems(codePage.stream().map(code -> {
            GetCodesRes.CodeItem codeItem = new GetCodesRes.CodeItem();
            codeItem.setCodeId(code.getId());
            codeItem.setUserId(code.getUser().getId());
            codeItem.setUsername(code.getUser().getUsername());
            codeItem.setName(code.getName());
            codeItem.setDescription(code.getDescription());
            codeItem.setVisibility(code.getVisibility());
            codeItem.setRegisterDateTime(code.getRegisterDateTime());
            return codeItem;
        }).toList());
        res.setPage(codePage.getNumber() + 1);
        res.setPageSize(codePage.getNumberOfElements());
        res.setPageSizeLimit(codePage.getSize());
        res.setTotalSize(codePage.getTotalElements());
        return ResponseEntity.ok(new BaseResponse<>(res));
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<PostCodeRes>> postCode(@Valid @RequestBody PostCodeReq req,
                                                              Principal principal) throws BaseException {
        PostCodeRes res = new PostCodeRes();
        res.setCodeId(
                this.codeService.createCode(Long.valueOf(principal.getName()), req.getName(), req.getDescription(),
                        req.getContent(), req.getVisibility()));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{codeId}")
                        .buildAndExpand(res.getCodeId())
                        .toUri())
                .body(new BaseResponse<>(res));
    }

    @GetMapping("/{codeId}")
    public ResponseEntity<BaseResponse<GetCodeRes>> getCode(@PathVariable Long codeId, Principal principal) {
        GetCodeRes res = new GetCodeRes();
        Long userId = (principal != null) ? Long.valueOf(principal.getName()) : null;
        CodeRevision codeRevision = this.codeProvider.findLatestCodeRevision(userId, codeId).orElse(null);

        if (codeRevision == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(BaseResponseStatus.RESPONSE_ERROR));
        }

        Code code = codeRevision.getCode();
        res.setCodeId(code.getId());
        res.setUserId(code.getUser().getId());
        res.setUsername(code.getUser().getUsername());
        res.setName(code.getName());
        res.setDescription(code.getDescription());
        res.setContent(codeRevision.getContent());
        res.setVisibility(code.getVisibility());
        res.setRegisterDateTime(code.getRegisterDateTime());
        res.setUpdateDateTime(codeRevision.getRegisterDateTime());
        return ResponseEntity.ok(new BaseResponse<>(res));
    }

    @PatchMapping("/{codeId}")
    public ResponseEntity<BaseResponse<PatchCodeRes>> patchCode(@PathVariable Long codeId,
                                                                @Valid @RequestBody PatchCodeReq req,
                                                                Principal principal) throws BaseException {
        PatchCodeRes res = new PatchCodeRes();
        Long userId = (principal != null) ? Long.valueOf(principal.getName()) : null;
        this.codeService.updateCode(userId, codeId, req.getName(), req.getDescription(), req.getVisibility());
        res.setCodeId(codeId);
        return ResponseEntity.ok(new BaseResponse<>(res));
    }

    @DeleteMapping("/{codeId}")
    public ResponseEntity<BaseResponse<?>> deleteCode(@PathVariable Long codeId, Principal principal)
            throws BaseException {
        Long userId = (principal != null) ? Long.valueOf(principal.getName()) : null;
        this.codeService.deleteCode(userId, codeId);
        return ResponseEntity.ok(new BaseResponse<>(BaseResponseStatus.SUCCESS));
    }

    @GetMapping(value = "/{codeId}/raw", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getCodeRaw(@PathVariable Long codeId, Principal principal) {
        Long userId = (principal != null) ? Long.valueOf(principal.getName()) : null;
        CodeRevision codeRevision = this.codeProvider.findLatestCodeRevision(userId, codeId).orElse(null);

        if (codeRevision == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }

        return ResponseEntity.ok(codeRevision.getContent());
    }
}

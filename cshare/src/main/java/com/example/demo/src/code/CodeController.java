package com.example.demo.src.code;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.code.model.Code;
import com.example.demo.src.code.model.GetCodesReq;
import com.example.demo.src.code.model.GetCodesRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/codes")
@RestController
public class CodeController {
    private final CodeProvider codeProvider;

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
            codeItem.setId(code.getId());
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
}

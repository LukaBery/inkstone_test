package kr.co.inkstone.member.controller;

import jakarta.validation.Valid;
import kr.co.inkstone.common.dto.ApiResponse;
import kr.co.inkstone.common.exception.DuplicateLoginIdException;
import kr.co.inkstone.member.domain.dto.request.MemberRequestDto;
import kr.co.inkstone.member.domain.dto.response.SignupSuccessResponseDto;
import kr.co.inkstone.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/member")
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/check-id")
    public ResponseEntity<ApiResponse<Map<String, Boolean>>>  checkDuplicateId(@RequestParam String loginId){

        if (!isValidLoginId(loginId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("아이디는 영문 + 숫자 조합 (6~12자)여야 합니다.", 400));
        }

        boolean isDuplicate = memberService.isLoginIdDuplicated(loginId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return  ResponseEntity.ok(ApiResponse.success(response));

    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> memberSignup(@Valid @RequestBody MemberRequestDto requestDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(ApiResponse.error("입력값이 유효하지 않습니다.", 400, errors));
        }

        if (!requestDto.isPasswordMatching()) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("비밀번호와 비밀번호 확인이 일치하지 않습니다.", 400));
        }

        SignupSuccessResponseDto response;
        try {
            response = memberService.registerMember(requestDto);
        }catch (DuplicateLoginIdException e ){
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage(), 400));

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }


    private boolean isValidLoginId(String loginId) {
        String regex = "^[a-zA-Z0-9]{6,12}$";
        return Pattern.matches(regex, loginId);
    }
}

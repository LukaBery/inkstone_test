package kr.co.inkstone.member.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MemberRequestDto(

        @NotBlank(message = "이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]{2,5}$|^[a-zA-Z]{2,20}$",
                message = "이름은 한글 2~5자 또는 영문 2~20자로 입력해주세요.")
        String name,

        @NotBlank(message = "아이디를 입력해주세요.")
        @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "아이디는 영문 + 숫자 조합 (6~12자)여야 합니다.")
        String loginId,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
                message = "비밀번호는 영문 + 숫자 + 특수문자를 포함하여 8~16자로 설정해야 합니다.")
        String password,

        @NotBlank(message = "비밀번호 확인을 입력해주세요.")
        String confirmPassword,

        @NotBlank(message = "전화번호를 입력해주세요.")
        @Pattern(regexp = "^\\d{8}$", message = "전화번호는 010을 제외한 8자리 숫자여야 합니다.")
        String phone
) {
    public boolean isPasswordMatching() {
        return password.equals(confirmPassword);
    }
}

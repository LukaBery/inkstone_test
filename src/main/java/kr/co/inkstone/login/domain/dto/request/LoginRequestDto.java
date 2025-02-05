package kr.co.inkstone.login.domain.dto.request;

public record LoginRequestDto(
        String loginId,
        String password
) {
}

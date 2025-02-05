package kr.co.inkstone.member.service;

import kr.co.inkstone.login.domain.dto.request.LoginRequestDto;
import kr.co.inkstone.member.domain.dto.request.MemberRequestDto;
import kr.co.inkstone.member.domain.dto.response.SignupSuccessResponseDto;
import kr.co.inkstone.member.domain.entity.Member;

public interface MemberService {
    boolean isLoginIdDuplicated(String loginId);
    SignupSuccessResponseDto registerMember(MemberRequestDto requestDto);
    Member findMemberByLoginId(LoginRequestDto requestDto);
}

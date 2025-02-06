package kr.co.inkstone.member.service;

import kr.co.inkstone.member.domain.dto.request.MemberEditRequestDto;
import kr.co.inkstone.member.domain.dto.request.MemberRequestDto;
import kr.co.inkstone.member.domain.dto.response.SignupSuccessResponseDto;

public interface MemberService {
    boolean isLoginIdDuplicated(String loginId);
    SignupSuccessResponseDto registerMember(MemberRequestDto requestDto);
    void editProfile(String loginId, MemberEditRequestDto requestDto);
}

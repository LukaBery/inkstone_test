package kr.co.inkstone.member.service.impl;

import jakarta.transaction.Transactional;
import kr.co.inkstone.common.exception.DuplicateLoginIdException;
import kr.co.inkstone.common.exception.InvalidPasswordException;
import kr.co.inkstone.common.exception.MemberNotExistException;
import kr.co.inkstone.login.domain.dto.request.LoginRequestDto;
import kr.co.inkstone.member.domain.dto.request.MemberRequestDto;
import kr.co.inkstone.member.domain.dto.response.SignupSuccessResponseDto;
import kr.co.inkstone.member.domain.entity.Member;
import kr.co.inkstone.member.repository.MemberRepository;
import kr.co.inkstone.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    public boolean isLoginIdDuplicated(String loginId) {

        return memberRepository.existsByLoginId(loginId);
    }

    @Transactional
    public SignupSuccessResponseDto registerMember(MemberRequestDto requestDto){

        if(isLoginIdDuplicated(requestDto.loginId())){
            throw new DuplicateLoginIdException();
        }
        Member member = memberRepository.save(new Member(requestDto));
        return new SignupSuccessResponseDto(member.getName());
    }

    @Override
    public Member findMemberByLoginId(LoginRequestDto requestDto) {

        Member member = memberRepository.findByLoginId(requestDto.loginId());

        if(member == null){
            throw new MemberNotExistException();
        }
        if(!requestDto.password().equals(member.getPassword())){
            throw new InvalidPasswordException();
        }

        return member;
    }
}

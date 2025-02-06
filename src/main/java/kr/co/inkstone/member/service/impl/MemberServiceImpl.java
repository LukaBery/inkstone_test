package kr.co.inkstone.member.service.impl;

import jakarta.transaction.Transactional;
import kr.co.inkstone.common.exception.DuplicateLoginIdException;

import kr.co.inkstone.common.exception.InvalidPasswordException;
import kr.co.inkstone.member.domain.dto.request.MemberEditRequestDto;
import kr.co.inkstone.member.domain.dto.request.MemberRequestDto;
import kr.co.inkstone.member.domain.dto.response.SignupSuccessResponseDto;
import kr.co.inkstone.member.domain.entity.Member;
import kr.co.inkstone.member.repository.MemberRepository;
import kr.co.inkstone.member.service.MemberService;
import kr.co.inkstone.security.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;



    public boolean isLoginIdDuplicated(String loginId) {

        return memberRepository.existsByLoginId(loginId);
    }

    @Override
    @Transactional
    public SignupSuccessResponseDto registerMember(MemberRequestDto requestDto){

        if(isLoginIdDuplicated(requestDto.loginId())){
            throw new DuplicateLoginIdException();
        }
        MemberRequestDto encodedDto = requestDto.encodePassword(passwordEncoder);

        Member member = memberRepository.save(new Member(encodedDto));
        return new SignupSuccessResponseDto(member.getName());
    }

    @Override
    @Transactional
    public void editProfile(String loginId, MemberEditRequestDto requestDto) {

        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + loginId));


        if(!passwordEncoder.matches(requestDto.password(), member.getPassword())){
            throw new InvalidPasswordException();
        }
        member.update(requestDto);

        CustomMemberDetails updatedUserDetails = new CustomMemberDetails(member);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                updatedUserDetails,
                null,
                updatedUserDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }


}

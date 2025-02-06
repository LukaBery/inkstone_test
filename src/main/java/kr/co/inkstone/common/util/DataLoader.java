package kr.co.inkstone.common.util;


import jakarta.transaction.Transactional;
import kr.co.inkstone.member.domain.dto.request.MemberRequestDto;
import kr.co.inkstone.member.domain.entity.Member;
import kr.co.inkstone.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (memberRepository.count() == 0) {
            Member admin = new Member(new MemberRequestDto(
                    "관리자",
                    "dlgksqls7218",
                    passwordEncoder.encode("dl232356!"),
                    passwordEncoder.encode("dl232356!"),
                    "12345678"
            ));
            memberRepository.save(admin);
        }
    }
}
package kr.co.inkstone.member.repository;

import kr.co.inkstone.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByLoginId(String loginId);
    Optional<Member> findByLoginId(String loginId);
}

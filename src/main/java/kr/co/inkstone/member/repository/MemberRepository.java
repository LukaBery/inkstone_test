package kr.co.inkstone.member.repository;

import kr.co.inkstone.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByLoginId(String loginId);
    Member findByLoginId(String loginId);
}

package kr.co.inkstone.member.domain.entity;

import jakarta.persistence.*;
import kr.co.inkstone.member.domain.dto.request.MemberEditRequestDto;
import kr.co.inkstone.member.domain.dto.request.MemberRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String role;

    public Member(MemberRequestDto dto) {
        this.loginId = dto.loginId();
        this.password = dto.password();
        this.name = dto.name();
        this.phone = dto.phone();
        this.role = "ROLE_MEMBER";
    }

    public void update(MemberEditRequestDto dto) {
        this.name = dto.name();
        this.phone = dto.phone();
    }
}

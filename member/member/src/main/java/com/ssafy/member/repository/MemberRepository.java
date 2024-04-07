package com.ssafy.member.repository;

import com.ssafy.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
// <어떤 엔티티 클래스를 사용할 것이냐, 그 엔티티 클래스의 PK 타입>

    // 이메일로 회원 정보 조회 (select * from member_table where member_email = ? )
    Optional<MemberEntity> findByMemberEmail(String memberEamil);

}

package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
//    회원 가입
//    회원 삭제
}

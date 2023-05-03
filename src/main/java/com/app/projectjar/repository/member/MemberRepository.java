package com.app.projectjar.repository.member;

import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

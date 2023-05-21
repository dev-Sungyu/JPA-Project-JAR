package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.MemberRandomKey;
import com.app.projectjar.entity.member.QMember;
import com.app.projectjar.entity.member.QMemberRandomKey;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.member.QMemberRandomKey.memberRandomKey1;

@RequiredArgsConstructor
@Slf4j
public class MemberRandomKeyQueryDslImpl implements MemberRandomKeyQueryDsl {
    private final JPAQueryFactory query;

    /* 회원의 가장 최근 랜덤키 찾기 */
    @Override
    public MemberRandomKey getListRandomKey(Long id) {
//        MemberRandomKey randomKey = query.select(memberRandomKey).from(member).where(member.id.eq(id)).orderBy(memberRandomKey.id.desc()).fetchFirst();
        MemberRandomKey randomKey = query.select(memberRandomKey1).from(memberRandomKey1).where(memberRandomKey1.member.id.eq(id)).orderBy(memberRandomKey1.id.desc()).fetchFirst();
            log.info("randomkey, reposi: " + randomKey);
        return randomKey;
    }
}

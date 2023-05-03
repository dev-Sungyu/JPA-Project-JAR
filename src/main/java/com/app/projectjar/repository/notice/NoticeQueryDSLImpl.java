package com.app.projectjar.repository.notice;


import com.app.projectjar.repository.member.MemberQueryDSL;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoticeQueryDSLImpl implements NoticeQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

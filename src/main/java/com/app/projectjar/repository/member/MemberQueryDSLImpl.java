package com.app.projectjar.repository.member;


import com.app.projectjar.repository.inquire.InquireQueryDSL;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Member;

@RequiredArgsConstructor
public class MemberQueryDSLImpl implements MemberQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

package com.app.projectjar.repository.inquire;


import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.entity.inquire.QInquire;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.inquire.QInquire.inquire;

@RequiredArgsConstructor
public class InquireQueryDslImpl implements InquireQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<Inquire> findAllWithPaging(Pageable pageable){
        List<Inquire> foundInquire = query.select(inquire)
                .from(inquire)
                .orderBy(inquire.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(inquire.count())
                .from(inquire)
                .fetchOne();
        return new PageImpl<>(foundInquire, pageable, count);
    }

}

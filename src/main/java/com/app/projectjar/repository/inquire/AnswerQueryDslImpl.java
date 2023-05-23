package com.app.projectjar.repository.inquire;


import com.app.projectjar.entity.inquire.Answer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

import static com.app.projectjar.entity.inquire.QAnswer.answer;

@RequiredArgsConstructor
public class AnswerQueryDslImpl implements AnswerQueryDsl {
    private final JPAQueryFactory query;

//    @Override
//    public Slice<Answer> findAllByAnswerWithPaging_QueryDsl(Long id, Pageable pageable) {
//        List<Answer> foundAnswer = query.select(answer)
//                .from(answer)
////                .leftJoin(answer., )
//                .fetchJoin()
//                .where(answer.inquire.id.eq(id))
//                .orderBy(answer.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize() + 1)
//                .fetch();
//
//    }
}

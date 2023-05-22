package com.app.projectjar.repository.inquire;

import com.app.projectjar.entity.inquire.Answer;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.inquire = :adminId AND a.id = :answerId")
    Answer findAnswerByAdminNameAndAnswerId(@Param("adminName") String adminName, @Param("answerId") Long answerId);
}

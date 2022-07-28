package com.sang.toeictest2.Repository;

import com.sang.toeictest2.Entity.Account;
import com.sang.toeictest2.Entity.Exam;
import com.sang.toeictest2.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByAccount(Account account);

    List<Result> findByExam(Exam exam);
}

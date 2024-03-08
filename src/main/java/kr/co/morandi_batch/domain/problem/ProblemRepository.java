package kr.co.morandi_batch.domain.problem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    @Query("""
            SELECT p.baekjoonProblemId
            FROM Problem p
            ORDER BY p.problemId DESC
            LIMIT 1
            """)
    Long findLastBaekjoonProblemId();
}

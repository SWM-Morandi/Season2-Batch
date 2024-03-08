package kr.co.morandi_batch.domain.problem;

import jakarta.persistence.*;
import kr.co.morandi_batch.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static kr.co.morandi_batch.domain.problem.ProblemStatus.INIT;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private Long problemId;

    @Column(name = "baekjoon_problem_id")
    private Long baekjoonProblemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "problem_tier")
    private ProblemTier problemTier;

    @Enumerated(EnumType.STRING)
    @Column(name = "problem_status")
    private ProblemStatus problemStatus;

    @Column(name = "solved_count")
    private Long solvedCount;

    @Builder
    private Problem(Long baekjoonProblemId, ProblemTier problemTier, ProblemStatus problemStatus, Long solvedCount) {
        this.baekjoonProblemId = baekjoonProblemId;
        this.problemTier = problemTier;
        this.problemStatus = problemStatus;
        this.solvedCount = solvedCount;
    }

    public void activate() {
        this.problemStatus = ProblemStatus.ACTIVE;
    }

    public static Problem create(Long baekjoonProblemId, ProblemTier problemTier, Long solvedCount) {
        return Problem.builder()
                .baekjoonProblemId(baekjoonProblemId)
                .problemTier(problemTier)
                .problemStatus(INIT)
                .solvedCount(solvedCount)
                .build();
    }
}

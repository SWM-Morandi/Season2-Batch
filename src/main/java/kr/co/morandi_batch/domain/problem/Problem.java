package kr.co.morandi_batch.domain.problem;

import jakarta.persistence.*;
import kr.co.morandi_batch.domain.algorithm.Algorithm;
import kr.co.morandi_batch.domain.common.BaseEntity;
import kr.co.morandi_batch.domain.problemalgorithm.ProblemAlgorithm;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static kr.co.morandi_batch.domain.problem.ProblemStatus.INIT;

@Entity
@Getter
@Slf4j
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

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<ProblemAlgorithm> problemAlgorithms = new ArrayList<>();

    @Builder
    private Problem(Long baekjoonProblemId, ProblemTier problemTier, ProblemStatus problemStatus, Long solvedCount) {
        this.baekjoonProblemId = baekjoonProblemId;
        this.problemTier = problemTier;
        this.problemStatus = problemStatus;
        this.solvedCount = solvedCount;
    }
    public void updateSolvedCount(Long solvedCount) {
        this.solvedCount = solvedCount;
    }
    public void updateProblemTier(ProblemTier problemTier) {
        this.problemTier = problemTier;
    }
    public void updateProblemAlgorithm(List<Algorithm> algorithms) {

        final Set<Integer> updatedAlgorithms = algorithms.stream()
                .map(Algorithm::getBojTagId)
                .collect(Collectors.toSet());

        // 새로 업데이트된 알고리즘에 포함되지 않는 알고리즘은 삭제
        getProblemAlgorithms().removeIf(pa -> !updatedAlgorithms.contains(pa.getAlgorithm().getBojTagId()));

        // 현재 알고리즘 Tag 목록
        final Set<Integer> nowAlgorithms = getProblemAlgorithms().stream()
                .map(ProblemAlgorithm::getAlgorithm)
                .map(Algorithm::getBojTagId)
                .collect(Collectors.toSet());

        // 새로 업데이트된 알고리즘 Tag 중 기존에 없던 Tag의 알고리즘은 추가
        algorithms.stream()
                .filter(pa -> !nowAlgorithms.contains(pa.getBojTagId()))
                .map(pa -> ProblemAlgorithm.create(pa, this))
                .forEach(pa -> this.problemAlgorithms.add(pa));

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

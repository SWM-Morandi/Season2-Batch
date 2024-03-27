package kr.co.morandi_batch.domain.problemalgorithm;

import jakarta.persistence.*;
import kr.co.morandi_batch.domain.algorithm.Algorithm;
import kr.co.morandi_batch.domain.common.BaseEntity;
import kr.co.morandi_batch.domain.problem.Problem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "problem_algorithm")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemAlgorithm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problemAlgorithmId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Algorithm algorithm;

    @ManyToOne(fetch = FetchType.LAZY)
    private Problem problem;

    public static ProblemAlgorithm create(Algorithm algorithm, Problem problem) {
        return ProblemAlgorithm.builder()
                .algorithm(algorithm)
                .problem(problem)
                .build();
    }

    @Builder
    private ProblemAlgorithm(Algorithm algorithm, Problem problem) {
        this.algorithm = algorithm;
        this.problem = problem;
    }
}

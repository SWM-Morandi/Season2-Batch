package kr.co.morandi_batch.updateBaekjoonProblem.reader.dto;

import kr.co.morandi_batch.domain.problem.Problem;
import kr.co.morandi_batch.domain.problem.ProblemTier;
import lombok.*;

import static kr.co.morandi_batch.domain.problem.ProblemStatus.ACTIVE;
import static kr.co.morandi_batch.domain.problem.ProblemStatus.INIT;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemDTO {
    private Long problemId;
    private String titleKo;
    private Integer level;
    private Integer acceptedUserCount;

    @Builder
    private ProblemDTO(Long problemId, String titleKo, Integer level, Integer acceptedUserCount) {
        this.problemId = problemId;
        this.titleKo = titleKo;
        this.level = level;
        this.acceptedUserCount = acceptedUserCount;
    }

    public Problem toEntity() {
        return Problem.builder()
                .baekjoonProblemId(problemId)
//                .title(titleKo)
                .problemStatus(ACTIVE)
                .problemTier(ProblemTier.of(level))
                .solvedCount(Long.valueOf(acceptedUserCount))
                .build();
    }


}

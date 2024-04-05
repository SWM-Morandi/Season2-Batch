package kr.co.morandi_batch.baekjoonproblemcontent;

import kr.co.morandi_batch.baekjoonproblemcontent.dto.ProblemDetails;
import kr.co.morandi_batch.baekjoonproblemcontent.reader.BaekjoonContentReader;
import kr.co.morandi_batch.domain.problem.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class BaekjoonProblemContentBatchConfig {

    private final BaekjoonContentReader baekjoonContentReader;

    @Bean
    Job getBaekjoonContentJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("baekjoonProblemContentsJob", jobRepository)
                .flow(getBaekjoonContentStep(jobRepository, transactionManager))
                .end()
                .build();
    }

    @Bean
    Step getBaekjoonContentStep(JobRepository jobRepository, PlatformTransactionManager transactionManger) {
        return new StepBuilder("baekjoonProblemContentsStep", jobRepository)
                .<ProblemDetails, ProblemDetails>chunk(10, transactionManger)
//                .allowStartIfComplete(true)
                .reader(baekjoonContentReader)
                .processor(item-> {
                    System.out.println(item);
                    return item;
                })
                .writer(item -> {
                    List<Problem> problems;
                })
                .build();
    }
}

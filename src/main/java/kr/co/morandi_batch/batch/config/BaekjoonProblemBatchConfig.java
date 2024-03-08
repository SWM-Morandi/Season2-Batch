package kr.co.morandi_batch.batch.config;

import kr.co.morandi_batch.batch.pagingCollectionsItemReader.PagingCollectionsItemReader;
import kr.co.morandi_batch.batch.processor.ProblemProcessor;
import kr.co.morandi_batch.domain.problem.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class BaekjoonProblemBatchConfig {

    private final PagingCollectionsItemReader<Problem, List<Problem>> problemPagingCollectionsItemReader;

    private final ProblemProcessor problemProcessor;

    @Bean
    Job updateBaekjoonProblemJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("updateBaekjoonProblem", jobRepository)
                .flow(getProblemStep(jobRepository, transactionManager))
                .end()
                .build();
    }

    @Bean
    Step getProblemStep(JobRepository jobRepository, PlatformTransactionManager transactionManger) {
        return new StepBuilder("getProblemStep", jobRepository)
                .<List<Problem>, List<Problem>>chunk(10, transactionManger)
                .allowStartIfComplete(true)
                .reader(problemPagingCollectionsItemReader)
                .processor(problemProcessor)
                .writer(list -> {
                    System.out.println("dd : ");
                })
                .build();
    }

}

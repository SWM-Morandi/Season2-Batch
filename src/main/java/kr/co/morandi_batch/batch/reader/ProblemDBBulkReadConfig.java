package kr.co.morandi_batch.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import kr.co.morandi_batch.batch.pagingCollectionsItemReader.PagingCollectionsItemReader;
import kr.co.morandi_batch.batch.pagingCollectionsItemReader.PagingCollectionsItemReaderBuilder;
import kr.co.morandi_batch.domain.problem.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ProblemDBBulkReadConfig {

    private final EntityManagerFactory emf;

    @Bean
    PagingCollectionsItemReader<Problem, List<Problem>> problemPagingCollectionsItemReader() {
        return new PagingCollectionsItemReaderBuilder<Problem,List<Problem>>()
                .entityManagerFactory(emf)
                .collectionClass(ArrayList.class)
                .chunkAndCollectionSize(10,50)
                .queryString("select p from Problem p ")
                .name("problemPagingCollectionsItemReader")
                .build();
    }

}

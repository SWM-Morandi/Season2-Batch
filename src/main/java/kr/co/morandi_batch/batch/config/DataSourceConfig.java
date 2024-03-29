package kr.co.morandi_batch.batch.config;

import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "batchDataSource")
    @BatchDataSource
    public DataSource H2Datasource () {
        return new EmbeddedDatabaseBuilder()
                .addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
    @Bean
    @Primary // 이 DataSource를 애플리케이션의 주 데이터 소스로 지정
    public DataSource dataSource(@Value("${spring.datasource.driver-class-name}") String driverClassName,
                                       @Value("${spring.datasource.url}") String url,
                                       @Value("${spring.datasource.username}") String user,
                                       @Value("${spring.datasource.password}") String pass) {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(user)
                .password(pass)
                .build();
    }
}

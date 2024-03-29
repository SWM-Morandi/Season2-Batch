package kr.co.morandi_batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MorandiBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MorandiBatchApplication.class, args);
    }

}

package gud.fun.junkdrawer.service.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CreateContentJobConfiguration<T> {

    @Autowired
    private CreateContentTasklet simpleTasklet;

    @Bean(name = "createContentJob")
    public Job createContentJob(JobRepository jobRepository, Step createContentStep) {
        return new JobBuilder("createContentJob", jobRepository)
                .start(createContentStep)
                .build();
    }

    @Bean
    public Step createContentStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("createContentStep", jobRepository)
                .tasklet(simpleTasklet, transactionManager)
                .build();
    }
}

package qcom.amfm.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import qcom.amfm.springbatch.listener.JobCompletionNotificationListener;
import qcom.amfm.springbatch.processor.CustomProcessor1;
import qcom.amfm.springbatch.reader.CustomerReader1;
import qcom.amfm.springbatch.writer.CustomWriter1;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public ResourcelessTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}

	@Bean
	public MapJobRepositoryFactoryBean mapJobRepositoryFactory(ResourcelessTransactionManager txManager)
			throws Exception {

		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(txManager);

		factory.afterPropertiesSet();

		return factory;
	}

	@Bean
	public JobRepository jobRepository(MapJobRepositoryFactoryBean factory) throws Exception {
		return factory.getObject();
	}
	
	@Bean
	public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}
	
	/*@Bean
	public ItemReader<FileEntity> customReader() {
		return new CustomReader();
	}*/
	
	@Bean
	public ItemReader<String> customReader() {
		return new CustomerReader1();
	}
	
	/*@Bean
	public CustomProcessor customProcessor() {
		return new CustomProcessor();
	}*/
	
	@Bean
	public CustomProcessor1 customProcessor() {
		return new CustomProcessor1();
	}
	
	/*@Bean
	public CustomWriter customWriter() {
		return new CustomWriter();
	}*/
	
	@Bean
	public CustomWriter1 customWriter() {
		return new CustomWriter1();
	}
	
	/*@Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
            .<FileEntity, FileEntity> chunk(10)
            .reader(customReader())
            .processor(customProcessor())
            .writer(customWriter())
            .build();
    }*/
	
	@Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
            .<String, String> chunk(10)
            .reader(customReader())
            .processor(customProcessor())
            .writer(customWriter())
            .build();
    }
	
	 @Bean
	    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
	        return jobBuilderFactory.get("fileProcessJob")
	            .incrementer(new RunIdIncrementer())
	            .listener(listener)
	            .flow(step1)
	            .end()
	            .build();
	    }
	

}

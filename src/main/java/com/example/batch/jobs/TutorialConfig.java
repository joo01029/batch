package com.example.batch.jobs;

import com.example.batch.tasklets.TutorialTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class TutorialConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	@Qualifier("tutorialJob")
	public Job tutorialJob(){
		return jobBuilderFactory.get("tutorialJob")
				.start(tutorialStep())
				.build();
	}

	@Bean
	public Step tutorialStep(){
		return stepBuilderFactory.get("tutorialStep")
				.tasklet(new TutorialTasklet())
				.build();
	}


	@Bean
	@Qualifier("multiJob")
	public Job multiJob(){
		return jobBuilderFactory.get("MultiSteps")
				.start(firstStep())
				.next(secondStep())
				.next(thirdStep())
				.build();
	}

	@Bean
	public Step firstStep(){
		return stepBuilderFactory.get("FirstStep")
				.tasklet((contribution, chunckContext)->{
					log.info("First Step!!");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	@Bean
	public Step secondStep(){
		return stepBuilderFactory.get("SecondStep")
				.tasklet((contribution, chunckContext)->{
					log.info("Second Step!!");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	@Bean
	public Step thirdStep(){
		return stepBuilderFactory.get("ThirdStep")
				.tasklet((contribution, chunckContext)->{
					log.info("Third Step!!");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
}

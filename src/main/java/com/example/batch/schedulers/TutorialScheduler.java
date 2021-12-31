package com.example.batch.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class TutorialScheduler {
	private final Job job;
	private final JobLauncher jobLauncher;

	@Scheduled(fixedDelay = 5*1000L)
	public void executeJob(){
		try{
			JobParameters parameter = new JobParametersBuilder()
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters();
			jobLauncher.run(
					job,
					parameter
			);
		}catch (JobExecutionException e){
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}

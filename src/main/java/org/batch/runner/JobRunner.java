package org.batch.runner;

import java.util.Calendar;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;



@Component
@AllArgsConstructor
public class JobRunner {
   
	private final  JobLauncher simpleJobLauncher;
     
	private final Job job;
	
	public void runBatchJob() {
		
		JobParametersBuilder parameters = new JobParametersBuilder();
		parameters.addDate("date", new Date() , true);
		runJob(job,parameters.toJobParameters());
		
	}
	
	private void runJob(Job job , JobParameters parameters) {
		
		try {
			JobExecution execution = simpleJobLauncher.run(job, parameters);
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
}

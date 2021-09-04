package org.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class jobListener extends JobExecutionListenerSupport {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("avant l'execution du job " + jobExecution.getJobInstance().getJobName());
		//jobExecution.getExecutionContext().putString("value", "this is the value");
		
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("a la fin de l'execution du job" //+ jobExecution.getExecutionContext().getString("value")
				);
	}
}

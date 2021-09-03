package org.batch.controller;

import java.util.Date;

import org.batch.runner.JobRunner;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.job.builder.JobBuilderException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/job")

public class EmployeeController {

	 @Autowired
	private JobRunner jobRunner;
	 @Autowired
	private  Job job;
	
	//private final JobRunner jobRunner;
	
	@RequestMapping("/demo1")
	public String runDemo1() throws Exception {
		jobRunner.runBatchJob();
		return "le job demo1 a été lancé avec succes";
	}
	
}

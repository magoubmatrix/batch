 package org.batch.job;

import java.io.IOException;
import java.net.MalformedURLException;

import org.batch.entity.Employee;
import org.batch.entity.EmployeeDto;
import org.batch.mapper.EmployeeRowMapperField;

import org.batch.writer.EmployeeWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;


import lombok.Data;




@Configuration
@Data

public class Demo {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
    private final ItemProcessor<EmployeeDto, Employee> employeeProcessor;
    private final EmployeeWriter employeeWriter;
   // @Autowired
    //private ItemReader<EmployeeDto> employeeReader;
   
    
	
   
     @Bean
	public Job demo1Job() throws Exception {
		return jobBuilderFactory.get("demo1").start(stepDemo1()).build();
	}
	
	
     @Bean
	public Step stepDemo1()  {
		return   stepBuilderFactory.get("step1")
				.<EmployeeDto,Employee>chunk(10)
				.reader(employeReader())
				.processor(employeeProcessor)
				.writer(employeeWriter)
				.faultTolerant().skipPolicy(skipPolicy())
				.build();
		}
	
     @Bean
	 public SkipPolicy skipPolicy() {
		return new JobSkipPolicy();
	}


	@Bean
	 @StepScope
     public Resource getSource(@Value("#{jobParameters[inputFile]}") final String source) {
    	 return new ClassPathResource(source);
     }
     
	@Bean
	@StepScope
	public FlatFileItemReader<EmployeeDto> employeReader()  {
		FlatFileItemReader<EmployeeDto> reader = new FlatFileItemReader<EmployeeDto>();
		reader.setResource(getSource(null));
		reader.setLineMapper(new DefaultLineMapper<EmployeeDto>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setDelimiter(",");
				setNames("firstname","lastname","email","age");
			}});
			setFieldSetMapper(new EmployeeRowMapperField());
		}});
		return reader;
	}


	@Bean
	public TaskExecutor executorTask() {
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(5);
		return asyncTaskExecutor;
	}

	
}

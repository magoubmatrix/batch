 package org.batch.job;



import javax.sql.DataSource;

import org.batch.entity.Employee;
import org.batch.entity.EmployeeDto;
import org.batch.mapper.EmployeeRowMapper;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;

import org.springframework.batch.item.database.JdbcCursorItemReader;

import org.springframework.batch.item.file.FlatFileItemWriter;

import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.FileSystemResource;

import org.springframework.core.io.Resource;


import lombok.Data;




@Configuration
@Data

public class Demo {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
    private final ItemProcessor<Employee, EmployeeDto> employeeProcessor;
    private final DataSource dataSource;
   // @Autowired
    //private ItemReader<EmployeeDto> employeeReader;
   
    
	
   
     @Bean
	public Job demo2Job() throws Exception {
		return jobBuilderFactory.get("demo2").start(stepDemo2()).build();
	}
	
	
     @Bean
	public Step stepDemo2() throws Exception  {
		return   stepBuilderFactory.get("step1")
				.<Employee,EmployeeDto>chunk(10)
				.reader(employeeDbReader())
				.processor(employeeProcessor)
				.writer(employeeFileWriter())
				.build();
		}
	
	
     private Resource outPutFile = new FileSystemResource("output/employee_out.csv");
     
     
     
	@Bean
	public ItemStreamReader<Employee> employeeDbReader()  {
	
		  JdbcCursorItemReader<Employee> reader = new JdbcCursorItemReader<>();
	        reader.setDataSource(dataSource);
	        reader.setSql("select * from employee");
	        reader.setRowMapper(new EmployeeRowMapper());
	        return reader;
	}
	
	
	@Bean
	public ItemWriter<EmployeeDto> employeeFileWriter() throws Exception {

	 FlatFileItemWriter<EmployeeDto> writer = new FlatFileItemWriter<>();
    writer.setResource(outPutFile);
    writer.setLineAggregator(new DelimitedLineAggregator<EmployeeDto>() {
        {
            setFieldExtractor(new BeanWrapperFieldExtractor<EmployeeDto>() {
                {
                    setNames(new String[]{"id", "firstname", "lastname", "email", "age"});
                }
            });
        }
    });
    writer.setShouldDeleteIfExists(true);
    return writer;
}

	
}

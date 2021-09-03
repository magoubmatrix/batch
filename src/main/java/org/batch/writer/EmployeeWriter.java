package org.batch.writer;

import java.util.List;

import org.batch.entity.Employee;
import org.batch.repository.EmployeeRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmployeeWriter implements ItemWriter<Employee> {

	private final EmployeeRepository employeeWriter;
	
	@Override
	public void write(List<? extends Employee> items) throws Exception {
		
	  employeeWriter.saveAll(items);
		
	}

}

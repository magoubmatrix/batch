package org.batch.processor;

import org.batch.entity.Employee;
import org.batch.entity.EmployeeDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeDto> {

	@Override
	public EmployeeDto process(Employee item) throws Exception {
		EmployeeDto emp = new EmployeeDto();

		emp.setFirstname(item.getFirstname());
		emp.setLastname(item.getLastname());
		emp.setEmail(item.getEmail());
		emp.setAge(item.getAge());
		return emp;
	}
	


}

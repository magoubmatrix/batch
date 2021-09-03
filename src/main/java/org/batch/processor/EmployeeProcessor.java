package org.batch.processor;

import org.batch.entity.Employee;
import org.batch.entity.EmployeeDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeDto, Employee> {

	@Override
	public Employee process(EmployeeDto item) throws Exception {
		Employee emp = new Employee();
		emp.setFirstname(item.getFirstname());
		emp.setLastname(item.getLastname());
		emp.setEmail(item.getEmail());
		emp.setAge(item.getAge());
		return emp;
	}

}

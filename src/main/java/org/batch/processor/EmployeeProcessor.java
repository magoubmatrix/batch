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
		//if(filterEmployeeByAge(25, item))
			//return emp = null;
		
		emp.setFirstname(item.getFirstname());
		emp.setLastname(item.getLastname());
		emp.setEmail(item.getEmail());
		emp.setAge(item.getAge());
		return emp;
	}
	
	
	public boolean filterEmployeeByAge( int ageLimit , EmployeeDto emp) {
		if(emp.getAge() < ageLimit)
			return true;
		else {
			return false;	
		}
		
	}

}

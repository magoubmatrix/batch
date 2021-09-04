package org.batch.mapper;

import org.batch.entity.EmployeeDto;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EmployeeRowMapperField implements FieldSetMapper<EmployeeDto> {

	@Override
	public EmployeeDto mapFieldSet(FieldSet fieldSet) throws BindException {
		EmployeeDto emp = new EmployeeDto();
		emp.setFirstname(fieldSet.readString("firstname"));
		emp.setLastname(fieldSet.readString("lastname"));
		emp.setEmail(fieldSet.readString("email"));
		emp.setAge(fieldSet.readInt("age"));
	
		return emp;
	}

}

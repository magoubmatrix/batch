package org.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.batch.entity.Employee;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setFirstname(resultSet.getString("firstname"));
        employee.setLastname(resultSet.getString("lastname"));
        employee.setEmail(resultSet.getString("email"));
        employee.setAge(resultSet.getInt("age"));
		return employee;
	}

	

}

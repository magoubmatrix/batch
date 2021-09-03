package org.batch.entity;

import lombok.Data;

@Data
public class EmployeeDto {

	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private int age;
}

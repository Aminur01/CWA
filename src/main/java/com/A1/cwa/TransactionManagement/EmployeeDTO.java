package com.A1.cwa.TransactionManagement;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EmployeeDTO {
	
	private String name;
	private Integer id;
	private Long salary;
	
	
	
	public EmployeeDTO() {
		super();
	}
	public EmployeeDTO(String name, Integer id, Long salary) {
		super();
		this.name = name;
		this.id = id;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeModel [name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append(", salary=");
		builder.append(salary);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}

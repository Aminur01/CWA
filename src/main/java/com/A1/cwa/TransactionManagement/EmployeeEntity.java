package com.A1.cwa.TransactionManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Employee")
public class EmployeeEntity {

	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long salary;
	
	
	
	
	public EmployeeEntity() {
		super();
	}

	public EmployeeEntity(String name, int id, long salary) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append(", salary=");
		builder.append(salary);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}

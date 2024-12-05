package com.A1.cwa.test;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeEntity {
	
	@Id
	private int id;
	private String name;
	private double salary;
	
	
	
	public EmployeeEntity() {
		super();
	}
	public EmployeeEntity(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeEntity [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", salary=");
		builder.append(salary);
		builder.append("]");
		return builder.toString();
	}
	
	

}

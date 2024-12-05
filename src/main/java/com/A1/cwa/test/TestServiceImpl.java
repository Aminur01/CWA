package com.A1.cwa.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeMapper employeeMapper;

	@Override
	public Optional<TestEmployeeModel> getEmployeeDetails(int id) {
		
		//Optional<TestEmployeeModel> empDetails=employeeRepository.findById(id);
		Optional<TestEmployeeModel> empDetails=employeeMapper.getTestEmployeeModel(employeeRepository.findEmpWithSecondHighestSalary()); 
		return empDetails;
		
		
	}

}

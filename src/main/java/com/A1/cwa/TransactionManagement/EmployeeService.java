package com.A1.cwa.TransactionManagement;

import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
	
	

	public int saveEmployeeDetails(EmployeeDTO employeeModel) throws Exception;
		
}

package com.A1.cwa.test;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface TestService {
	
	public Optional<TestEmployeeModel> getEmployeeDetails(int id);

}

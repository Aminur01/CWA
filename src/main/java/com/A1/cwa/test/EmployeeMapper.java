package com.A1.cwa.test;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
	
	@Autowired
	ModelMapper mapper;
	
	public Optional<TestEmployeeModel> getTestEmployeeModel(Optional<EmployeeEntity> entity) {
		
		return entity.map(employee -> mapper.map(employee, TestEmployeeModel.class));
		
		
	}

}

package com.A1.cwa.TransactionManagement;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.A1.cwa.exception.TestTransactionException;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	ActualEmployeeRepository employeeRepository;
	
	private static final ModelMapper modelMapper = new ModelMapper();

	@Override
    @Transactional(rollbackFor = Exception.class)
	public int saveEmployeeDetails(EmployeeDTO employeeModel) throws Exception {
		
		EmployeeEntity entity=employeeRepository.save(transformToEntity(employeeModel));
//		if(true) {
//			throw new TestTransactionException("Exception while saving in DB, rolled back");
//		}
		
		return entity.getId();
	}

	private EmployeeEntity transformToEntity(EmployeeDTO employeeModel) {
		
		return modelMapper.map(employeeModel, EmployeeEntity.class);
		
		
		
	}

}

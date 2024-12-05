package com.A1.cwa.test;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
	
	Optional<TestEmployeeModel> findById(int id);
	
	@Query(value = "SELECT * FROM employee_entity e WHERE e.salary = (SELECT DISTINCT salary FROM employee_entity ORDER BY salary DESC LIMIT 1 OFFSET 1)", nativeQuery = true)
	Optional<EmployeeEntity> findEmpWithSecondHighestSalary();
}

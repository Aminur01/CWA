package com.A1.cwa.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cwa/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@PreAuthorize("@AuthorizationComponent.checkUserAccess('test_cwa_method')")
	@GetMapping("/emp/{idm}")
	public ResponseEntity<Optional<TestEmployeeModel>> getEmployeeDetails(@PathVariable("idm") int idm, @RequestParam(required = false) Integer id){
		
		
		System.out.println("inside test method");
		
		
		return new ResponseEntity<Optional<TestEmployeeModel>>(testService.getEmployeeDetails(1), HttpStatus.OK);
		
	}

}

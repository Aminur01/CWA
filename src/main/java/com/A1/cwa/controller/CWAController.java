package com.A1.cwa.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.A1.cwa.TransactionManagement.EmployeeDTO;
import com.A1.cwa.TransactionManagement.EmployeeService;
import com.A1.cwa.TransactionManagement.Student;
import com.A1.cwa.model.ErrorResponse;
import com.A1.cwa.utils.Constants;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Validated
public class CWAController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CWAController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	/*
	 * http://localhost:8090/swagger-ui/index.html
	 */

	/*
	 * eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
	 * eyJzdWIiOiIxMjM0NTY3ODkwIiwiYXVkaXRUcmFja2luZ0lkIjoiSm9obiBEb2UiLCJvdSI6MTUxNjIzOTAyMiwiY3dhcm9sZXMiOlsiVEVTVCBDV0EiXX0
	 * .SJX4bgzm6FqMe2IFT51FllCSrwhk6QyZnpzD8hM6fNQ
	 */

	@ApiResponse(responseCode = "200", description = "get method to test auth", content = {
			@Content(schema = @Schema(implementation = ErrorResponse.class)) })
	@PreAuthorize("@AuthorizationComponent.checkUserAccess('test_cwa_method')")
	@GetMapping(Constants.ATHORIZED_GET)
	public String authenticatedMethod() {
		logger.info("inside test method");
		return "Hi";
	}

	@ApiResponse(responseCode = "200", description = "get method to test un auth", content = {
			@Content(schema = @Schema(implementation = ErrorResponse.class)) })
	@PreAuthorize("@AuthorizationComponent.checkUserAccess('test_cwa_method_non')")
	@GetMapping(Constants.UN_ATHORIZED_GET)
	public String nonAuthenticatedMethod() {
		logger.info("inside test1 method");
		return "Hi";
	}
	
	
	@ApiResponse(responseCode = "200", description = "get method to test auth", content = {
			@Content(schema = @Schema(implementation = ErrorResponse.class)) })
	@PreAuthorize("@AuthorizationComponent.checkUserAccess('test_cwa_method')")
	@PostMapping(Constants.POST_EMPLOYEE_DETAILS)
	public ResponseEntity<Integer> saveEmployeeDetails(@RequestBody EmployeeDTO employeeModel) throws Exception {

		return new ResponseEntity<Integer>(employeeService.saveEmployeeDetails(employeeModel), HttpStatus.OK);
	}
	

}

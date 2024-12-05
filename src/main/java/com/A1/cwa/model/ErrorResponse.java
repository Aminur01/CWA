package com.A1.cwa.model;

import lombok.Data;

@Data
public class ErrorResponse {

	private int status;
	private String error;
	private String message;

}

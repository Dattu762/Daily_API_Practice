package com.DailyPract.GlobalExceptionHandler;

public class ProductAlreadyFoundException extends RuntimeException {
	
	public ProductAlreadyFoundException(String message) {
		super(message);
	}

}

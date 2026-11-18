package com.DailyPract.GlobalExceptionHandler;

public class ProductAlreadyDeletedException extends RuntimeException{
	
	public ProductAlreadyDeletedException(String message) {
		super(message);
	}

}

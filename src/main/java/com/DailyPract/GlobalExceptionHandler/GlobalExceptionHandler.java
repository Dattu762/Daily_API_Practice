package com.DailyPract.GlobalExceptionHandler;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.DailyPract.Util.ApiResponse;

import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex){
		
		Map<String, String> errors= ex.getBindingResult()
		.getFieldErrors()
		.stream()
		.collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));
		
//		Map<String, String> errors2=new HashMap<>();
//		ex.getBindingResult().getFieldErrors().forEach(error -> errors2.put( error.getField(), error.getDefaultMessage()));
//		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.onFailure("Validation Failed", errors));
		
	}
	
	@ExceptionHandler(exception = ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotFoundProduct(ResourceNotFoundException ex){	
		
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND) 
				.body(ApiResponse.onFailure(ex.getMessage(), null));
	}
	
	@ExceptionHandler(exception = ProductAlreadyFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateProduct(ProductAlreadyFoundException ex){
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiResponse.onFailure(ex.getMessage(), null));
	}
	
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleGeneral(Exception ex){	
		
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()) 
				.body(ApiResponse.onFailure("Something went wrong. Please try again later.", null));
	}
	
	@ExceptionHandler(exception = ProductAlreadyDeletedException.class)
	public ResponseEntity<ApiResponse<Void>> handlealreadyDeletedProduct(Exception ex){
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiResponse.onFailure(ex.getMessage(), null));
	}
}


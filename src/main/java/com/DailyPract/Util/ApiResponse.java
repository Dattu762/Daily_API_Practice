package com.DailyPract.Util;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApiResponse<T> {
	
	private Boolean success;
	
	private String message;
	
	private T data;
	
	private Map<String, String> fieldErrors;
	
	private LocalDateTime timeStamp;
	
	public static <T> ApiResponse<T> onSuccess(String message, T body){
		
		return ApiResponse.<T>builder()
				.success(true)
				.message(message)
				.data(body)
				.timeStamp(LocalDateTime.now())
				.build();
		
	}
	
	public static <T> ApiResponse<T> onFailure(String message, Map<String,String> fieldErrors){
		return ApiResponse.<T>builder()
				.success(false)
				.message(message)
				.data(null)
				.fieldErrors(fieldErrors)
				.timeStamp(LocalDateTime.now())
				.build();
	}
	
}

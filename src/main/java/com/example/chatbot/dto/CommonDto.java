package com.example.chatbot.dto;

import java.util.List;

public class CommonDto {

	
	
	private String status;
	private String message;
	private Object responseData;
	private List<?> responseDto;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResponseData() {
		return responseData;
	}
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
	public List<?> getResponseDto() {
		return responseDto;
	}
	public void setResponseDto(List<?> responseDto) {
		this.responseDto = responseDto;
	}
	
	
}

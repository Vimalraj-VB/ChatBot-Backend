package com.example.chatbot.dto.service;

import com.example.chatbot.dto.CommonDto;
import com.example.chatbot.dto.UserLoginDto;

import jakarta.servlet.http.HttpServletRequest;

public interface UserLoginService {

	CommonDto saveAlluserDetails(UserLoginDto dto, HttpServletRequest request);

}

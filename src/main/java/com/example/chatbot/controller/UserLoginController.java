package com.example.chatbot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.chatbot.dto.CommonDto;
import com.example.chatbot.dto.UserLoginDto;
import com.example.chatbot.dto.service.UserLoginService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserLoginController {

	@Resource
	UserLoginService loginserivce;

	@RequestMapping(value = { "/api/saveUserDetails", "saveUserDetails" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public ResponseEntity<?> saveUserDetails(@RequestBody UserLoginDto dto,HttpServletRequest request) {
		CommonDto response = loginserivce.saveAlluserDetails(dto,request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

package com.example.chatbot.dto.serviceImpl;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.chatbot.communication.OtpMailSending;
import com.example.chatbot.dto.CommonDto;
import com.example.chatbot.dto.UserLoginDto;
import com.example.chatbot.dto.service.UserLoginService;
import com.example.chatbot.entity.User_Login;
import com.example.chatbot.reposotory.UserLoginRepository;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	
	@Resource
	UserLoginRepository loginRepo;

	@Resource
	OtpMailSending mailservice;

	@Override
	public CommonDto saveAlluserDetails(UserLoginDto dto, HttpServletRequest request) {
		CommonDto response = new CommonDto();
		this.logger.info("=======>>>  Enter into the saveAlluserDetails  <<<=======");
		try {
			User_Login entity = new User_Login();
			entity.setCreation_date(new Date());
			String encodedEmail = Base64.getEncoder().encodeToString(dto.getEmail().getBytes());
			entity.setName(dto.getName());
			entity.setEmail(encodedEmail);
			entity.setAuthorized(dto.getAuthorized());
			String encodedpass = Base64.getEncoder().encodeToString(dto.getPassword().getBytes());
			entity.setPassword(encodedpass);
			String otp = this.SecureOTP();
			String encodedotp = Base64.getEncoder().encodeToString(otp.getBytes());
			entity.setOtp(encodedotp);
			entity.setStatus("Y");
			for (String files : dto.getProfile_pic()) {
				String[] parts = files.split(",");
				String[] newParts = Arrays.copyOf(parts, parts.length + 1);
				String type = newParts[0];
				type = type.replace("\"", "").replace("[", "").replace("]", "");
				String base64 = newParts[1];
				base64 = base64.replace("\"", "").replace("[", "").replace("]", "");
				byte[] decodedBytes = Base64.getDecoder().decode(base64);
				entity.setProfile_pic(decodedBytes);
				String fileName = null;
				if (type.equalsIgnoreCase("data:application/pdf;base64")) {
					fileName = "PDF";
				} else if (type.equalsIgnoreCase("data:image/png;base64")) {
					fileName = "PNG";
				} else if (type.equalsIgnoreCase("data:image/jpeg;base64")) {
					fileName = "JPEG";
				}
               entity.setFileName(fileName);
			}
			
			loginRepo.save(entity);
			response.setStatus("Success");
			String mailstatus = mailservice.sendOtp(otp, dto.getEmail(), dto.getName());
			response.setMessage(mailstatus);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("Failed");
			response.setMessage(e.getMessage());
			this.logger.info("Error =======>>>  "+e.getMessage());

		}
		this.logger.info("=======>>>  End into the saveAlluserDetails  <<<=======");
		return response;
	}

	private String SecureOTP() {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

}

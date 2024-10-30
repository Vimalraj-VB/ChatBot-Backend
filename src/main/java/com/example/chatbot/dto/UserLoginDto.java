package com.example.chatbot.dto;

import java.util.Date;

public class UserLoginDto {

	private String name;
	private String password;
	private String email;
	private String status;
	private int otp;
	private String authorized;
	private String[] profile_pic;
	private Date creation_date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getAuthorized() {
		return authorized;
	}

	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}

	public String[] getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String[] profile_pic) {
		this.profile_pic = profile_pic;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

}

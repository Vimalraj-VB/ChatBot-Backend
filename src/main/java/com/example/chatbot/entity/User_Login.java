package com.example.chatbot.entity;

import java.util.Date;

import com.example.chatbot.dto.CommonDto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Table(name = "user_login")
@Entity
public class User_Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private String status;

	@Basic
	@Column(name = "otp",nullable = false,length = 10)
	private String otp;

	@Basic
	@Column(name = "authorized",nullable = false,length = 10)
	private String authorized;
	
	@Basic
	@Column(name = "fileName",nullable = false,length = 10)
	private String fileName;
	
	@Basic
	@Column(name = "otpStatus",length = 50)
	private String otpStatus;

	@Column(name = "profile_pic", nullable = false,columnDefinition="LONGBLOB")
	@Basic
	@Lob
	private byte[] profile_pic;

	@Column(name = "creation_date")
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



	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getAuthorized() {
		return authorized;
	}

	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}

	public byte[] getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(byte[] profile_pic) {
		this.profile_pic = profile_pic;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public CommonDto orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOtpStatus() {
		return otpStatus;
	}

	public void setOtpStatus(String otpStatus) {
		this.otpStatus = otpStatus;
	}
	
	

}

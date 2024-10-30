package com.example.chatbot.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatbot.entity.User_Login;

public interface UserLoginRepository extends JpaRepository<User_Login, Long> {

}

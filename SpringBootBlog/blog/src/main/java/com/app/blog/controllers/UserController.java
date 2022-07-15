package com.app.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.dto.LoginDto;
import com.app.blog.dto.RegisterUserDTO;
import com.app.blog.models.Users;
import com.app.blog.repository.UserRepository;
import com.app.blog.util.EntitiyHawk;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 1460344
 */
@RestController
@RequestMapping("/")
public class UserController extends EntitiyHawk {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity signup(@RequestBody RegisterUserDTO registerUserDTO)
	{
		Users user = new Users();
		user.setUserName(registerUserDTO.getName());
		user.setPassword(registerUserDTO.getPassword());
		user.setEmail(registerUserDTO.getEmail());
		
		userRepository.save(user);
		return this.genericSuccess();
	}
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDto loginDto)
	{
		System.out.println("inside login....before");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
		loginDto.getEmail(),loginDto.getPassword()));
		System.out.println("inside login....between");
        SecurityContextHolder.getContext().setAuthentication(authentication);
   
        return this.genericSuccess();
	} 
}

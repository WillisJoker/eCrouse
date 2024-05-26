package com.nutc.Ecrouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutc.Ecrouse.model.User;
import com.nutc.Ecrouse.model.request.AuthenticationRequest;
import com.nutc.Ecrouse.model.request.LoginRequest;
import com.nutc.Ecrouse.model.request.RegisterRequest;
import com.nutc.Ecrouse.model.response.AuthenticationResponse;
import com.nutc.Ecrouse.model.response.StatusResponse;
import com.nutc.Ecrouse.service.AuthenticationService;
import com.nutc.Ecrouse.service.JwtService;
import com.nutc.Ecrouse.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private AuthenticationService service;

	@PostMapping("/register")
	public ResponseEntity<StatusResponse> register(@RequestBody @Validated RegisterRequest request) {
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody @Validated LoginRequest request) {
		return ResponseEntity.ok(service.login(request));
	}
	
}

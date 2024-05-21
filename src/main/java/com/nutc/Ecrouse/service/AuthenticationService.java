package com.nutc.Ecrouse.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nutc.Ecrouse.dao.UserRepository;
import com.nutc.Ecrouse.model.User;
import com.nutc.Ecrouse.model.request.LoginRequest;
import com.nutc.Ecrouse.model.request.RegisterRequest;
import com.nutc.Ecrouse.model.response.AuthenticationResponse;
import com.nutc.Ecrouse.model.response.StatusResponse;

@Service
public class AuthenticationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	public StatusResponse register(RegisterRequest request) {

		User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getUserName(),
				request.getUserRole());
		userRepository.save(user);
		return new StatusResponse("成功");
	}

	/**
	 * 登入
	 */
	public AuthenticationResponse login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse("成功", jwtToken);
	}
}

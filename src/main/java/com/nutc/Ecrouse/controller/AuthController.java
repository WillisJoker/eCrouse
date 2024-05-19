package com.nutc.Ecrouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutc.Ecrouse.model.AuthenticationRequest;
import com.nutc.Ecrouse.model.AuthenticationResponse;
import com.nutc.Ecrouse.model.User;
import com.nutc.Ecrouse.service.JwtService;
import com.nutc.Ecrouse.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getName(),
					authenticationRequest.getPassword()));
		} catch (AuthenticationException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getName());
		final String jwt = jwtService.generateToken(userDetails.getUsername());
		return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);

	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.saveUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

}

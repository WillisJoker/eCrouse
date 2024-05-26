package com.nutc.Ecrouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutc.Ecrouse.dao.UserRepository;
import com.nutc.Ecrouse.model.User;
import com.nutc.Ecrouse.model.User.AppUserRole;
import com.nutc.Ecrouse.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/user")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<AppUserRole> role() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("-----------------------------");
		User user = (User) userService.loadUserByUsername(authentication.getName());
		System.out.println(user.getUserRole());
		System.out.println("-----------------------------");
		return ResponseEntity.ok(user.getUserRole());
	}

}

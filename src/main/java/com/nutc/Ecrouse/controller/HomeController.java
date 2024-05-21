package com.nutc.Ecrouse.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

	@GetMapping("/test")
	@SecurityRequirement(name = "Bearer Authentication")
	public String home() {
		return "HomePage";
	}

}

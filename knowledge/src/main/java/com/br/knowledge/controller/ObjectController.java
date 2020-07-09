package com.br.knowledge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.knowledge.model.AuthRequest;
import com.br.knowledge.util.JwtUtil;


@RestController
public class ObjectController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PreAuthorize("hasRole('admin')")
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to knowledge system";
	}

	@CrossOrigin("*")
	@PostMapping("/authenticate")	
	public String genereteToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("invalid username/password ---> " + e);
		}
		return jwtUtil.generateToken(authRequest.getUsername());
	}

}

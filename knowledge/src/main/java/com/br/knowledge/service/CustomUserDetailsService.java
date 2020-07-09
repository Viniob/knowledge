package com.br.knowledge.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.knowledge.model.User;
import com.br.knowledge.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return new
				org.springframework.security.core.userdetails.User
				//	(user.getUsername(), user.getPassword(), new ArrayList<>());
				(user.getUsername(), user.getPassword(), getAuthority(user));
		
		
	}

	private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        if(user.getRoles() != null) {
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		});
        }
		return authorities;
	}
	
}

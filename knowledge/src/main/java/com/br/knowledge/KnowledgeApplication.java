package com.br.knowledge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.br.knowledge.model.User;
import com.br.knowledge.repository.UserRepository;

@SpringBootApplication
public class KnowledgeApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void initUsers() {
		List<String> rolesVini = new ArrayList<>();
		rolesVini.add("admin");
		rolesVini.add("user");
		List<String> rolesVittor = new ArrayList<>();
		rolesVittor.add("user");
		List<User> users = 
				Stream.of(new User(1, "vinicius", "123", "vinao_ob@hotmai.com", rolesVini),
						new User(2, "vittor", "321", "sa_l_va@hotmai.com", rolesVittor))
				.collect(Collectors.toList());
		userRepository.deleteAll();
		userRepository.saveAll(users);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowCredentials(true);
			}
		};
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(KnowledgeApplication.class, args);
	}

}

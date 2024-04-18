package com.example.salesBackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("package com.example.salesBackend.Entity;")
@EnableJpaRepositories("com.example.salesBackend.Repo")
public class SalesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesBackendApplication.class, args);

	}

}
  
package com.infosys.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InfosysCoreDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfosysCoreDataApplication.class, args);
	}
}

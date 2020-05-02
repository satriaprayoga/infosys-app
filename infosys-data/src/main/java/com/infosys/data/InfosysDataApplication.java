package com.infosys.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InfosysDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfosysDataApplication.class, args);
	}

}

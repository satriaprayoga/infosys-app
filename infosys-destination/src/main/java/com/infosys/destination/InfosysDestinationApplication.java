package com.infosys.destination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InfosysDestinationApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfosysDestinationApplication.class, args);
	}
}

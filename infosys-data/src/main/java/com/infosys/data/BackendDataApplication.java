package com.infosys.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.infosys.data.event.TourEventSource;

@SpringBootApplication
@EnableDiscoveryClient
public class BackendDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDataApplication.class, args);
	}

}

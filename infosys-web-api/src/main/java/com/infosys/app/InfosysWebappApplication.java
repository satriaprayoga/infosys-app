package com.infosys.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.infosys.app.config.AppProperties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties(AppProperties.class)
public class InfosysWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfosysWebappApplication.class, args);
	}

}

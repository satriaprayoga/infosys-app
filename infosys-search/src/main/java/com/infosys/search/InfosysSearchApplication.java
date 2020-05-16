package com.infosys.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.infosys.search.config.SearchProperties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(value= {SearchProperties.class})
public class InfosysSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfosysSearchApplication.class, args);
	}
}

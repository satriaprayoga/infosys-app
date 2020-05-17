package com.infosys.search.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "search")
public class SearchProperties {

	private String elasticsearchUri;
	private String username;
	private String password;
}

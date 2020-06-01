package com.infosys.webclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.midtrans.Config;
import com.midtrans.ConfigFactory;
import com.midtrans.service.MidtransCoreApi;

@Configuration
public class MidtransConfig {

	@Autowired
	private AppProperties appProperties;
	
	@Bean
	public MidtransCoreApi midtransCoreApi() {
		return new ConfigFactory(new Config(appProperties.getMidtrans().getServerKey(), 
				appProperties.getMidtrans().getClientKey(), 
				appProperties.getMidtrans().isProduction())).getCoreApi();
	}
}

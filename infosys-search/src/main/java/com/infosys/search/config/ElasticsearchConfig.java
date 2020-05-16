package com.infosys.search.config;

import java.io.IOException;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentProperty;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mapping.context.MappingContext;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableElasticsearchRepositories
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration{
	
	private final SearchProperties searchProperties;
	
	public ElasticsearchConfig(SearchProperties searchProperties) {
		this.searchProperties=searchProperties;
	}

	@Bean
	public RestHighLevelClient elasticsearchClient() {
		ClientConfiguration clientConfiguration=ClientConfiguration.builder()
												.connectedTo(searchProperties.getElasticsearchUri())
												.build();
		return RestClients.create(clientConfiguration).rest();
	}
	
	@Bean
	public EntityMapper entityMapper() {
		return new CustomEntityMapper(elasticsearchMappingContext(), new DefaultConversionService());
	}
	
	@Bean
	public ElasticsearchOperations elasticsearchOperations() {
		return new ElasticsearchRestTemplate(elasticsearchClient(), entityMapper());
	}
	
	
	public static class CustomEntityMapper extends ElasticsearchEntityMapper{

		private final ObjectMapper objectMapper;
		public CustomEntityMapper(
				MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext,
				GenericConversionService conversionService) {
			super(mappingContext, conversionService);
			objectMapper=new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		    objectMapper.registerModule(new JavaTimeModule());
		}
		
		@Override
	    public String mapToString(Object object) throws IOException {
	      return objectMapper.writeValueAsString(object);
	    }

	    @Override
	    public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
	      return objectMapper.readValue(source, clazz);
	    }
	}


}

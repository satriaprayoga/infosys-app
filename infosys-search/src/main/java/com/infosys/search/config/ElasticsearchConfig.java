package com.infosys.search.config;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPost;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.core.geo.CustomGeoModule;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableElasticsearchRepositories
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration{
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	private final SearchProperties searchProperties;
	
	public ElasticsearchConfig(SearchProperties searchProperties) {
		this.searchProperties=searchProperties;
	}

	@Bean
	public RestHighLevelClient elasticsearchClient() {
		//InetSocketAddress endpoint=new InetSocketAddress("", 443);
//		ClientConfiguration clientConfiguration=ClientConfiguration
//				.builder()
//				.connectedTo(HttpHost.create(searchProperties.getElasticsearchUri()).toHostString())
//				.usingSsl()
//				.withBasicAuth(searchProperties.getUsername(), searchProperties.getPassword())
//												.build();
		//return RestClients.create(clientConfiguration).rest();
		return RestClients.create(ClientConfiguration.builder().connectedTo(HttpHost.create(searchProperties.getElasticsearchUri()).toHostString()).build()).rest();
	}
	
	@Bean
	public EntityMapper entityMapper() {
		ElasticsearchEntityMapper mapper=new ElasticsearchEntityMapper(elasticsearchMappingContext(), new DefaultConversionService());
	
		mapper.setConversions(new ElasticsearchCustomConversions(Arrays.asList(DateToStringConverter.INSTANCE,StringToDateConverter.INSTANCE)));
		return mapper;
		//return new CustomEntityMapper(elasticsearchMappingContext(), new DefaultConversionService());
	}
	
	
	
	@Bean
	public ElasticsearchOperations elasticsearchOperations() {
		return new ElasticsearchRestTemplate(elasticsearchClient(), new CustomerEntityMapper() /*entityMapper()*/);
	}
	
	@WritingConverter
	enum DateToStringConverter implements Converter<Date, String>{
		INSTANCE;
		@Override
		public String convert(Date source) {
			return formatter.format(source);
		}

		
	}
	
	@ReadingConverter
	enum StringToDateConverter implements Converter<String,Date>{
		INSTANCE;
		@Override
		public Date convert(String source) {
			try {
				return formatter.parse(source);
			} catch (ParseException e) {
				return null;
			}
			
		}
		
	}
	
	public static class CustomerEntityMapper implements EntityMapper{
		
		 private final ObjectMapper objectMapper;
		 
		 public CustomerEntityMapper() {
			objectMapper=new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.registerModule(new CustomGeoModule());
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

		@Override
		public Map<String, Object> mapObject(Object source) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T readObject(Map<String, Object> source, Class<T> targetType) {
			// TODO Auto-generated method stub
			return null;
		}

		

		
		
	}
	
	/*
	 * public static class CustomEntityMapper extends ElasticsearchEntityMapper{
	 * 
	 * private final ObjectMapper objectMapper; public CustomEntityMapper(
	 * MappingContext<? extends ElasticsearchPersistentEntity<?>,
	 * ElasticsearchPersistentProperty> mappingContext, GenericConversionService
	 * conversionService) { super(mappingContext, conversionService);
	 * objectMapper=new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	 * false);
	 * objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
	 * true); objectMapper.registerModule(new JavaTimeModule());
	 * objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	 * 
	 * }
	 * 
	 * @Override public String mapToString(Object object) throws IOException {
	 * return objectMapper.writeValueAsString(object); }
	 * 
	 * @Override public <T> T mapToObject(String source, Class<T> clazz) throws
	 * IOException { return objectMapper.readValue(source, clazz); } }
	 */


}

package com.infosys.search.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import lombok.Data;

@Data
@Document(indexName = "destination")
@Setting(settingPath = "es-config/destination.json")
public class Destination {

	@Id
	private String id;
	
	@Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "autocomplete_search")
	private String destination;
	
	@Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "autocomplete_search")
	private String location;
	@Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "autocomplete_search")
	private String description;
	@Field(type = FieldType.Keyword)
	private String code;
	@Field(type = FieldType.Keyword)
	private String company;
	@Field(type = FieldType.Keyword)
	private String email;
	@Field(type = FieldType.Keyword)
	private String phone;
	
	@Field(type = FieldType.Keyword)
	private String [] accessabilities;
}

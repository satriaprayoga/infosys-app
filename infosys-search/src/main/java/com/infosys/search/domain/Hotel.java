package com.infosys.search.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import lombok.Data;

@Data
@Document(indexName = "hotel")
@Setting(settingPath = "es-config/destination.json")
public class Hotel {

	@Id 
	private String id;

	@Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "autocomplete_search")
	private String destination;
	@Field(type = FieldType.Keyword)
	private String destinationId;
	
	@Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "autocomplete_search")
	private String location;
	
	//@Field(type = FieldType.Keyword)
	//private String [] landmarks;
	//@Field(type = FieldType.Keyword)
	//private String [] additionals;
	@Field(type = FieldType.Keyword)
	private String group;
	@Field(type = FieldType.Keyword)
	private String name;
	
	@Field(type=FieldType.Boolean)
	private boolean published=false;
	
	@Field(type = FieldType.Keyword)
	private String unit;
	
	@Field(type=FieldType.Long)
	private long price;
	
	@Field(type=FieldType.Integer)
	private int minOrder;
	@Field(type=FieldType.Integer)
	private int capacity;
	
}

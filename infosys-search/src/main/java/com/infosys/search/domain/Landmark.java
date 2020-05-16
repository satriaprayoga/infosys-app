package com.infosys.search.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import lombok.Data;

@Document(indexName = "landmark")
@Setting(settingPath = "es-config/destination.json")
@Data
public class Landmark {

	@Id
	private String id;
	@Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "autocomplete_search")
	private String landmark;
	@Field(type=FieldType.Text)
	private String description;
	@Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "autocomplete_search")
	private String destination;
	@Field(type=FieldType.Keyword)
	private String destinationCode;
	@Field(type=FieldType.Integer)
	private Integer hour;
	@Field(type=FieldType.Integer)
	private Integer minute;
}

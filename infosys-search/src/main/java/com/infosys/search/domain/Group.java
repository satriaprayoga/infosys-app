package com.infosys.search.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import lombok.Data;

@Data
@Document(indexName = "group")
@Setting(settingPath = "es-config/destination.json")
public class Group {
	
	@Id
	private String id;
	@Field(type = FieldType.Keyword)
	private String name;
	@Field(type = FieldType.Keyword)
	private String destination;
	@Field(type = FieldType.Keyword)
	private String destinationId;
}

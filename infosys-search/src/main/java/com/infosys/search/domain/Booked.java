package com.infosys.search.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(indexName = "booked")
@Setting(settingPath = "es-config/destination.json")
public class Booked {
	
	@Id
	private String id;
	
	@Field(type = FieldType.Keyword)
	private String packageId;
	@Field(type=FieldType.Integer)
	private int currentCapacity;
	@Field(type = FieldType.Date, store = true, format = DateFormat.date)
	private Date bookDate;
	
}

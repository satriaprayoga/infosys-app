package com.infosys.webclient.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse implements Serializable{

	private static final long serialVersionUID = 8253315459335498914L;
	private HttpStatus status;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
    private LocalDateTime timestamp;
    private String message;
    private Object response;
    
    public ApiResponse(HttpStatus status,String message,Object resp) {
    	this.status=status;
    	this.message=message;
    	this.timestamp=LocalDateTime.now();
    	this.response=resp;
    }
    
    public ApiResponse(HttpStatus status, Object resp) {
    	this.status=status;
    	this.timestamp=LocalDateTime.now();
    	this.response=resp;
    }
    
}

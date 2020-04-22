package com.infosys.app.dto;

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
public class ApiResponse {

	private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
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

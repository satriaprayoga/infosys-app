package com.infosys.webclient.dto.payload;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerInfoRequest {

	@NotNull
	private String country;
	@NotNull
	private String state;
	@NotNull
	private String city;
	@NotNull
	private String zipCode;
	@NotNull
	private String address;
}

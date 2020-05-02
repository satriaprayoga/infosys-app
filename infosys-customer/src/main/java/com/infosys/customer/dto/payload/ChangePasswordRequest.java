package com.infosys.customer.dto.payload;

import lombok.Data;

@Data
public class ChangePasswordRequest {

	private String id;
	private String password;
}

package com.infosys.webclient.dto.payload;

import lombok.Data;

@Data
public class ChangePasswordRequest {

	private String id;
	private String password;
}

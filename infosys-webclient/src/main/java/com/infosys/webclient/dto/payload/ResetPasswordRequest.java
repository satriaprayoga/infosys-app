package com.infosys.webclient.dto.payload;

import lombok.Data;

@Data
public class ResetPasswordRequest {

	private String email;
	private String confirmUrl;
}

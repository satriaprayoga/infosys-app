package com.infosys.data.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageResponse {

	private String id;
	private String filename;
	private String downloadUri;
	private String fileType;
	private long size;
}

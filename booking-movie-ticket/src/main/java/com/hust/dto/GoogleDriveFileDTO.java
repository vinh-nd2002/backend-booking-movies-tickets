package com.hust.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleDriveFileDTO {

//	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String link;
	private String size;
	private String thumbnailLink;
	private boolean shared;
}

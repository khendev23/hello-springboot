package com.sh.app.ws.dto;

import lombok.Data;

@Data
public class Payload {

	private PayloadType type;
	private String from;
	private String to;
	private String content;
	private long createdAt;
	
	
}

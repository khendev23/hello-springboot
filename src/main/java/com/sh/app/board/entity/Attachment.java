package com.sh.app.board.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
	private int id;
	private int boardId;
	private String originalFilename;
	private String renamedFilename;
	private LocalDateTime createdAt;
}

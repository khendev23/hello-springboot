package com.sh.app.todo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
	private int id;
	private String memberId;
	private String todo;
	private LocalDateTime createdAt;
	private LocalDateTime completedAt;
	
}

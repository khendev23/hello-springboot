package com.sh.app.todo.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.sh.app.todo.entity.Todo;

import lombok.Data;

@Data
public class TodoUpdateDto {
	@NotNull(message = "Todo 아이디는 필수입니다.")
	private Integer id;
	private boolean completed;
	
	public Todo toTodo() {
		return Todo.builder()
				.id(id)
				.completedAt(completed ? LocalDateTime.now() : null)
				.build();
	}
}

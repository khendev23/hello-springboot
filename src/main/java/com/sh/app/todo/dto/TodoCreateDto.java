package com.sh.app.todo.dto;

import javax.validation.constraints.NotBlank;

import com.sh.app.todo.entity.Todo;

import lombok.Data;

@Data
public class TodoCreateDto {
	@NotBlank(message = "할일은 필수 입력값입니다.")
	private String todo;

	public Todo toTodo() {
		return Todo.builder()
				.todo(todo)
				.build();
	}
}

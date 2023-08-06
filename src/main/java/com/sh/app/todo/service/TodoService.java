package com.sh.app.todo.service;

import java.util.List;

import com.sh.app.todo.entity.Todo;

public interface TodoService {

	List<Todo> findAllByMemberId(String memberId);

	int insertTodo(Todo todo);

	int updateTodo(Todo todo);

	int deleteTodo(Todo todo);

}

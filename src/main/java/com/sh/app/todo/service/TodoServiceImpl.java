package com.sh.app.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.app.todo.entity.Todo;
import com.sh.app.todo.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public List<Todo> findAllByMemberId(String memberId) {
		return todoRepository.findAllByMemberId(memberId);
	}

	@Override
	public int insertTodo(Todo todo) {
		return todoRepository.insertTodo(todo);
	}

	@Override
	public int updateTodo(Todo todo) {
		return todoRepository.updateTodo(todo);
	}

	@Override
	public int deleteTodo(Todo todo) {
		return todoRepository.deleteTodo(todo);	
	}
}

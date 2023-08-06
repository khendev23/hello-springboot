package com.sh.app.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sh.app.member.entity.MemberDetails;
import com.sh.app.todo.dto.TodoCreateDto;
import com.sh.app.todo.dto.TodoUpdateDto;
import com.sh.app.todo.entity.Todo;
import com.sh.app.todo.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todo.do")
	public void todo(
			@AuthenticationPrincipal MemberDetails member, 
			Model model) { 
		if(true)
			throw new RuntimeException("Todododododododoodo");
		
		// 의존주입 받은 todoService
		log.debug("todoService = {} {}", todoService, todoService.getClass());
		
//		if(member.getMemberId() == null) 
//			throw new RuntimeException("memberId is null");
		
		List<Todo> todos = todoService.findAllByMemberId(member.getMemberId());
		model.addAttribute("todos", todos);
	}
	
	@PostMapping("/todoCreate.do")
	public String create(
			@Valid TodoCreateDto _todo, 
			BindingResult bindingResult, 
			@AuthenticationPrincipal MemberDetails member, 
			RedirectAttributes redirectAttr) {
		Todo todo = _todo.toTodo();
		todo.setMemberId(member.getMemberId());
		int result = todoService.insertTodo(todo);
		return "redirect:/todo/todo.do";
	}
	@PostMapping("/todoUpdate.do")
	public String update(
			@Valid TodoUpdateDto _todo, 
			BindingResult bindingResult, 
			@AuthenticationPrincipal MemberDetails member, 
			RedirectAttributes redirectAttr) { 
		Todo todo = _todo.toTodo();
		todo.setMemberId(member.getMemberId());
		log.debug("todo = {}", todo);
		int result = todoService.updateTodo(todo);
		return "redirect:/todo/todo.do";
	}
	@PostMapping("/todoDelete.do")
	public String delete(
			@RequestParam int id, 
			@AuthenticationPrincipal MemberDetails member, 
			RedirectAttributes redirectAttr) { 
		Todo todo = Todo.builder()
					.id(id)
					.memberId(member.getMemberId())
					.build();
		int result = todoService.deleteTodo(todo);
		return "redirect:/todo/todo.do";
	}
}

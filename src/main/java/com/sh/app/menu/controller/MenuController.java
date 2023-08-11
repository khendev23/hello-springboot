package com.sh.app.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sh.app.menu.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("menu")
@Slf4j
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@GetMapping("/menu.do")
	public void menu() {}
	
	@GetMapping("/findAll.do")
	public ResponseEntity<?> findAll() {
		return menuService.findAll();
	}
	
}

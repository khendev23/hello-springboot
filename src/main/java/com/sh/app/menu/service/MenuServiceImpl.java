package com.sh.app.menu.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

	private String MENU_HOST = "http://localhost:10000";
	
	@Override
		public ResponseEntity<?> findAll() {
			RestTemplate restTemplate = new RestTemplate();
			String uri = MENU_HOST + "/menus";
			return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Map<String, Object>>>(){});
		}
}

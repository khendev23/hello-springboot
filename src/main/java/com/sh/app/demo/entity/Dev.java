package com.sh.app.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity클래스는 db테이블과 동일한 구조여야한다.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dev {
	private int id;
	private String name;
	private int career;
	private String email;
	private Gender gender;
	private List<String> lang;
	private LocalDateTime createdAt;
}

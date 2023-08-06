package com.sh.app.demo.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sh.app.demo.entity.Gender;

import lombok.Data;

@Data
public class DevUpdateDto {
	@NotNull(message = "아이디는 필수입니다.")
	private Integer id;
	
	@NotNull(message = "이름은 필수입니다.")
	@Pattern(regexp = "[가-힣]{2,}", message = "이름은 한글 2글자이상이어야 합니다.")
	private String name;
	
	@NotNull(message = "경력은 필수입니다.")
	@Min(value = 1, message = "경력은 0 이하의 숫자일수 없습니다.")
	private Integer career;
	
	@NotBlank(message = "이메일은 필수입니다. 빈문자열/공백문자열은 허용하지 않습니다.")
	private String email;
	
	private Gender gender;
	
	@NotNull(message = "하나 이상의 언어를 선택해주세요")
	private List<String> lang;

	
}

package com.sh.app.demo.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sh.app.demo.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto클래스는 요청/응답의 규격을 정의한 클래스 
 * - 용도별로 여러개 만들어 사용가능. DevCreateDto, DevUpdateDto, ...
 *
 * 유효성검사
 * 문자관련
 * @NotNull null허용하지 않음 / "" " " 허용
 * @NotEmpty null "" 허용하지 않음 / " " 허용
 * @NotBlank null "" " " 허용하지 않음
 * @Pattern 정규식 int/Integer타입 사용불가
 * @Size 문자열길이
 * 
 * 숫자관련
 * @Min
 * @Max
 * @Positive
 * @Negative
 * 
 * 논리관련
 * @AssertTrue
 * @AssertFalse
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevCreateDto {
	@NotNull(message = "이름은 필수입니다.")
	@Pattern(regexp = "[가-힣]{2,}", message = "이름은 한글 2글자이상이어야 합니다.")
	private String name;
	
	@NotNull(message = "경력은 필수입니다.")
	@Min(value = 1, message = "경력은 0 이하의 숫자일수 없습니다.")
	private Integer career;
	
	@NotNull(message = "이메일은 필수입니다.")
	private String email;
	
	private Gender gender;
	@NotNull(message = "하나 이상의 언어를 선택해주세요")
	private List<String> lang;
	
}

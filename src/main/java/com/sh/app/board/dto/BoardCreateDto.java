package com.sh.app.board.dto;

import javax.validation.constraints.NotBlank;

import com.sh.app.board.entity.Board;
import com.sh.app.member.entity.Member;

import lombok.Data;

@Data
public class BoardCreateDto {

	@NotBlank(message = "제목은 필수입니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수입니다.")
	private String content;

	public Board toBoard() {
		
		return Board.builder()
			.title(title)
			.content(content)
			.build();
		
	}
	
}

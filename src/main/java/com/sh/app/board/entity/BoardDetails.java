package com.sh.app.board.entity;

import java.util.List;

import com.sh.app.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // 부모필드까지 설정가능한 Builder
public class BoardDetails extends Board{

	private Member member;
	private int attachCount;
	private List<Attachment> attachments;
}

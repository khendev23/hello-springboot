package com.sh.app.board.service;

import java.util.List;
import java.util.Map;

import com.sh.app.board.entity.Attachment;
import com.sh.app.board.entity.Board;
import com.sh.app.board.entity.BoardDetails;

public interface BoardService {

	List<BoardDetails> findAllboard(Map<String, Object> params);

	int insertBoard(Board board);

	BoardDetails findById(int id);

	Attachment findAttachmentById(int id);

}

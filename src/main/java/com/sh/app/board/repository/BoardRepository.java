package com.sh.app.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.session.RowBounds;

import com.sh.app.board.entity.Attachment;
import com.sh.app.board.entity.Board;
import com.sh.app.board.entity.BoardDetails;

@Mapper
public interface BoardRepository {
	
	
	@Select("select b.*, (select count(*) from attachment a where (a.board_id = b.id) )as attachCount  from board b order by id desc")
	List<BoardDetails> findAllboard(RowBounds rowBounds);

	@Insert("insert into board values(seq_board_id.nextval, #{title}, #{memberId}, #{content}, default)")
	@SelectKey(
			before = false,
			keyProperty = "id",
			resultType = int.class,
			statement = "select seq_board_id.currval from dual")
	int insertBoard(Board board);

	@Insert("insert into attachment values(seq_attachment_id.nextval, #{boardId}, #{originalFilename}, #{renamedFilename}, default)")
	int insertAttachment(Attachment attach);

	BoardDetails findById(int id);

	@Select("select * from attachment where id=#{id}")
	Attachment findAttachmentById(int id);

}

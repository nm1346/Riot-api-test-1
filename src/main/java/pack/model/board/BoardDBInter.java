package pack.model.board;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pack.Controller.BoardBean;

public interface BoardDBInter {
	
	@Select("select * from board"
			+ " where id=#{id} and"
			+ " board_delete=0"
			+ " order by board_num desc"
			+ " limit #{page},#{pagesize}")
	public List<BoardDto> getBoardListAll(BoardBean bean);
	@Select("select count(*) from board where board_delete=0 and id=#{id}")
	public int getBoardAllCount(BoardBean bean);
	
	@Select("select * from board"
			+ " where board_delete=0 and id=#{id} and board_category like #{board_category}"
			+ " and ${search_category} like CONCAT('%',#{search_value},'%')"
			+ " order by board_num desc"
			+ " limit #{page},#{pagesize}")
	public List<BoardDto> getBoardSearchList(BoardBean bean);
	@Select("select count(*) from board"
			+ " where board_delete=0 and id=#{id} and board_category=#{board_category}"
			+ " and ${search_category} like CONCAT('%',#{search_value},'%')")
	public int getBoardSearchListCount(BoardBean bean);
	
	@Select("select * from board where board_delete=0 and board_num=#{board_num}")
	public BoardDto getBoardByBoardnum(BoardBean bean);
	
	@Insert("insert into board"
			+ "(board_writer,board_password,board_subject,board_content,"
			+ "board_ip,board_wdate,id,board_category)"
			+ " values(#{board_writer},#{board_password},"
			+ "#{board_subject},#{board_content},#{board_ip},"
			+ "now(),#{id},#{board_category}) ")
	public boolean insertBoard(BoardBean bean);
	
	@Update("update board set"
			+ " board_subject=#{board_subject},board_content=#{board_content},"
			+ " board_category=#{board_category} "
			+ " where board_num=#{board_num} and board_password=#{board_password}")
	public boolean updateBoard(BoardBean bean);
	
	@Update("update board set"
			+ " board_delete=1"
			+ " where board_num=#{board_num}"
			+ " and board_password=#{board_password}")
	public boolean deleteBoard(BoardBean bean);
	
}

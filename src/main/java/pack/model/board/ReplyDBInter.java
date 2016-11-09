package pack.model.board;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pack.Controller.ReplyBean;

public interface ReplyDBInter {
	@Select("select * from reply where board_num=#{board_num} and reply_delete=0 order by gnum")
	public List<ReplyDto> getReplyList(int board_num);
	
	@Insert("insert into reply"
			+ "(reply_writer,reply_password,reply_content,reply_ip,"
			+ "reply_wdate,board_num,reply_gnum)"
			+ "values(#{reply_writer},#{reply_password},#{reply_content},#{reply_ip},"
			+ " now(),#{board_num},#{reply_gnum})")
	public boolean insertReply(ReplyBean bean);
	
	@Update("update reply set"
			+ " reply_content=#{reply_content} "
			+ " where reply_password=#{reply_password} and reply_delete=0")
	public boolean updateReply(ReplyBean bean);
	
	@Update("update reply set"
			+ " reply_delete=1 "
			+ " where reply_password=#{reply_password} and reply_num=#{reply_num}")
	public boolean deleteReply(ReplyBean bean);
	
}

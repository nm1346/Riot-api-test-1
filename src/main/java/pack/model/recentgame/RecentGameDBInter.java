package pack.model.recentgame;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface RecentGameDBInter {
	
	@Insert("insert into ")
	public boolean insertGame(String summonerId);
}

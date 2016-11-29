package pack.model.challenger;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface ChallengerDBInter {
	@Select("select * from challenger order by leaguePoints desc")
	public List<ChallengerDto> selectChallenger();

	@Delete("delete from challenger")
	public int deleteChallenger();

	@Insert("insert into challenger values(#{tier}, #{playerOrTeamName}, #{leaguePoints}, #{wins}, #{losses}, now())")
	public int insertMaster(ChallengerDto dto);

	@Insert("insert into challenger values(#{tier}, #{playerOrTeamName}, #{leaguePoints}, #{wins}, #{losses}, now())")
	public int insertChallenger(ChallengerDto dto);

}

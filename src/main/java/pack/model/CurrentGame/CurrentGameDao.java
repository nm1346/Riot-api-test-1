package pack.model.CurrentGame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
//DB 요ㅊ처리용 Repository
@Repository
public class CurrentGameDao  {
	
	
	@Autowired
	private CurrentGameDBInter summonerDBInter;
	
	public String selectchampionName(Long id) throws DataAccessException {
		return summonerDBInter.selectChampion(id);
	}
	
	public String selectchampionKey(Long id) throws DataAccessException {
		return summonerDBInter.selectChampionKey(id);
	}
	
	public String selectSummonerSpell(Long id) throws DataAccessException{
		return summonerDBInter.selectSummonerSpell(id);
	}
	
	public String gameName(Long queueType) throws DataAccessException{
		return summonerDBInter.gamename(queueType);
	}
	
	public String tier(Long id) throws DataAccessException{
		return summonerDBInter.Tier(id);
	}
	
}

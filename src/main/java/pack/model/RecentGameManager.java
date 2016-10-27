package pack.model;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Game.RecentGames;
import pack.Controller.SummonerBean;
import pack.model.recentgame.RecentApiDao;
import pack.model.recentgame.RecentGameDao;
import pack.model.summoner.SummonerApiDao;
import pack.model.summoner.SummonerDto;

@Service
public class RecentGameManager {
	
	@Autowired
	RecentApiDao apiDao;
	@Autowired
	RecentGameDao gameDao;
	@Autowired
	SummonerApiDao summonerApiDao;
	
	public HashMap<String, Object> getRecentGames(SummonerBean bean){
		HashMap<String, Object> map = new HashMap<>();
		try {
			SummonerDto dto = summonerApiDao.ApigetSummonerByName(bean.getName());
			map.put("RecentGames", apiDao.getRecentGame(dto.getId()));
		} catch (RiotApiException e) {
			map.put("error Code : ", e.getErrorCode());
			map.put("error Message : ", e.getMessage());
		} finally {
			return map;
		}
	}
	
	
	
	
	
}

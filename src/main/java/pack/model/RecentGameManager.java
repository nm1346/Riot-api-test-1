package pack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Game.Game;
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
		RecentGames games = null;
		List<Game> game;
		try {
			SummonerDto dto = summonerApiDao.ApigetSummonerByName(bean.getName());
			System.out.println(dto.getId());
			games = apiDao.ApigetRecentGame(dto.getId());
			game = new ArrayList<>(games.getGames());
			map.put("RecentGames", games);
			System.out.println(game.size());
		} catch (RiotApiException e) {
			map.put("error Code : ", e.getErrorCode());
			map.put("error Message : ", e.getMessage());
		} 
			return map;
	}
	
	
	
	
	
}

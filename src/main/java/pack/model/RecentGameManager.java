package pack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.RawStat;
import net.rithms.riot.dto.Game.RawStats;
import net.rithms.riot.dto.Game.RecentGames;
import pack.Controller.SummonerBean;
import pack.model.recentgame.GameDto;
import pack.model.recentgame.RecentApiDao;
import pack.model.recentgame.RecentGameDao;
import pack.model.summoner.SummonerApiDao;
import pack.model.summoner.SummonerDto;

@Service
public class RecentGameManager {
	
	/*@Autowired
	RecentApiDao apiDao;
	@Autowired
	RecentGameDao gameDao;
	@Autowired
	SummonerApiDao summonerApiDao;
	
	public HashMap<String, Object> getRecentGames(SummonerBean bean){
		HashMap<String, Object> map = new HashMap<>();
		RecentGames games = null;
		List<Game> game = null;
		try {
			SummonerDto summoner = summonerApiDao.ApigetSummonerByName(bean.getName());
			System.out.println(summoner.getId());
			games = apiDao.ApigetRecentGame(summoner.getId());
			game = new ArrayList<>(games.getGames());
			gameDao.insertRecentGame(summoner.getId(), game);
			List<GameDto> gamelist = gameDao.selectRecentGames(summoner.getId());
			for (int i = 0; i < gamelist.size(); i++) {
				gamelist.get(i).setFellowPlayers(gameDao.selectFellowPlayer(gamelist.get(i).getGameId()));
				gamelist.get(i).setRawstats(gameDao.selectRawstats(gamelist.get(i).getGameId()));
			}
			map.put("recentgamelist", gamelist);
		} catch (RiotApiException e) {
			map.put("error Code : ", e.getErrorCode());
			map.put("error Message : ", e.getMessage());
		} 
			return map;
	}
	이제 필요없음
	*/
	
	
	
}

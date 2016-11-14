package pack.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.RecentGames;
import net.rithms.riot.dto.Static.SummonerSpell;
import net.rithms.riot.dto.Static.SummonerSpellList;
import pack.Controller.SummonerBean;
import pack.model.recentgame.GameDto;
import pack.model.recentgame.RecentApiDao;
import pack.model.recentgame.RecentGameDao;
import pack.model.summoner.LeagueDto;
import pack.model.summoner.SummonerApiDao;
import pack.model.summoner.SummonerDao;
import pack.model.summoner.SummonerDto;

@Service
public class SummonerManager {
	
	@Autowired
	SummonerApiDao summonerapiDao;
	@Autowired
	SummonerDao summonerDao;
	@Autowired
	RecentApiDao RecentapiDao;
	@Autowired
	RecentGameDao gameDao;
	public Map<String, Object> getSummonerAndLeague(SummonerBean bean) {
		HashMap<String, Object> map = new HashMap<>();
		RecentGames games = null;
		List<Game> game = null;
		LeagueDto dto = null;
		SummonerDto summoner = summonerDao.selectSummoner(bean);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		if (summoner != null) {
			Date date = null;
			try {
				date = format.parse(summoner.getSearchDate());
				summoner.setSearchDate(format.format(date));
			} catch (Exception e) {
				System.out.println("parsing err" + e);
			}
			Calendar searchDate = Calendar.getInstance();
			searchDate.setTime(date);
			searchDate.add(Calendar.MINUTE, 2);
			if (searchDate.getTime().before(new Date())) {
				try {

					summoner = summonerapiDao.ApigetSummonerByName(bean.getName());
					dto = summonerapiDao.ApigetLeagueData(summoner.getId());
					summonerDao.updateSummoner(summoner);
					summoner.setSearchDate(format.format(new Date()));
					LeagueDto leaguecheck = summonerDao.selectLeagueData(summoner.getId());
					if (dto != null && leaguecheck != null) {
						summonerDao.updateLeague(dto);
					} else if (dto != null) {
						summonerDao.insertLeague(dto);
					}
					games = RecentapiDao.ApigetRecentGame(summoner.getId());
					game = new ArrayList<>(games.getGames());
					gameDao.insertRecentGame(summoner.getId(), game);
				} catch (RiotApiException e) {
					System.out.println("getSummonerAndLeague ApiGetUpdate Error" + e);
					map.put("success", "0");
					map.put("errormsg", e.getMessage());
					map.put("errorcode", e.getErrorCode());
					return map;
				}
			}
			map.put("summonerData", summoner);
			LeagueDto leaguedata = summonerDao.selectLeagueData(summoner.getId());
			if (leaguedata != null) {
				map.put("leagueData", leaguedata);
			} else {
				map.put("leagueData", "집계된 리그데이터는 존재하지않습니다.");
			}
			
			List<GameDto> gamelist = gameDao.selectRecentGames(summoner.getId());
			for (int i = 0; i < gamelist.size(); i++) {
				gamelist.get(i).setFellowPlayers(gameDao.selectFellowPlayer(gamelist.get(i).getGameId()));
				gamelist.get(i).setRawstats(gameDao.selectRawstats(gamelist.get(i).getGameId()));
			}
			map.put("recentgamelist", gamelist);
			map.put("success", "1");
		} else {
			try {
				summoner = summonerapiDao.ApigetSummonerByName(bean.getName());
				dto = summonerapiDao.ApigetLeagueData(summoner.getId());
				summonerDao.insertSummoner(summoner);
				summoner.setSearchDate(format.format(new Date()));
				if (dto != null) {
					summonerDao.insertLeague(dto);
				}
				games = RecentapiDao.ApigetRecentGame(summoner.getId());
				game = new ArrayList<>(games.getGames());
				gameDao.insertRecentGame(summoner.getId(), game);
				map.put("summonerData", summoner);
				LeagueDto leaguedata = summonerDao.selectLeagueData(summoner.getId());
				if (leaguedata != null) {
					map.put("leagueData", leaguedata);
				} else {
					map.put("leagueData", "집계된 리그데이터는 존재하지않습니다.");
				}
				List<GameDto> gamelist = gameDao.selectRecentGames(summoner.getId());
				for (int i = 0; i < gamelist.size(); i++) {
					gamelist.get(i).setFellowPlayers(gameDao.selectFellowPlayer(gamelist.get(i).getGameId()));
					gamelist.get(i).setRawstats(gameDao.selectRawstats(gamelist.get(i).getGameId()));
				}
				map.put("recentgamelist", gamelist);
				map.put("success", "1");
			} catch (RiotApiException e) {
				map.put("success", "0");
				map.put("errormsg", e.getMessage());
				map.put("errorcode", e.getErrorCode());
			}
		}
		return map;
	}

}

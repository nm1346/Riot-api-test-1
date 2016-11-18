package pack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.FeaturedGames.CurrentGameInfo;
import net.rithms.riot.dto.FeaturedGames.FeaturedGames;
import net.rithms.riot.dto.FeaturedGames.Participant;
import net.rithms.riot.dto.Static.ChampionList;
import pack.model.featuredgames.FeaturedDBInter;
import pack.model.featuredgames.FeaturedGamesApiDao;

@Service
public class FeaturedGameManager {
	@Autowired
	FeaturedGamesApiDao api;
	@Autowired
	FeaturedDBInter dbinter;
	
	public Map<String,Object> getFeaturedGame(){
		HashMap<String, Object> map=new HashMap<>();
		try {
			FeaturedGames list= api.ApiGetList();
			if(list==null){
				map.put("success", false);
				map.put("errorMessage", "게임중인 유저가 없습니다");
			}else{
				ArrayList<String> nameList=new ArrayList<>();
				for(CurrentGameInfo g:list.getGameList()){
					for(Participant p:g.getParticipants()){
						System.out.println(p.getSummonerName());
						nameList.add(p.getSummonerName());
					}
				}
				System.out.println(nameList.get(0));
				map.put("success", true);
				map.put("featuredList", list);
				map.put("leagueIdList",dbinter.selectLeagueSummoner(nameList));
			}
		} catch (RiotApiException e) {
			map.put("success", false);
			map.put("errorMessage", e.getMessage());
			map.put("errorCode", e.getErrorCode());
		}
		return map;
	}
}

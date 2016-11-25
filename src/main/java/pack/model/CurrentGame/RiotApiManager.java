package pack.model.CurrentGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.PlatformId;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.CurrentGame.CurrentGameInfo;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Static.ChampionList;
import net.rithms.riot.dto.Static.MasteryList;
import net.rithms.riot.dto.Static.SummonerSpell;
import net.rithms.riot.dto.Static.SummonerSpellList;
import net.rithms.riot.dto.Summoner.Summoner;
import pack.model.RiotApiKeyRotate;


@Repository
public class RiotApiManager {
	@Autowired
	RiotApiKeyRotate api;
	
	public Summoner ApigetSummonerByName(String name) throws RiotApiException{
		return api.getSummonerByName(name);
	}
	//인게임정보
	public CurrentGameInfo ApiGameInfo(Long id) throws RiotApiException{
		CurrentGameInfo gameInfo = api.getCurrentGameInfo(PlatformId.KR, id);
		return gameInfo;
	}

	//스펠정보
	public SummonerSpellList getSummonerSpell(){
		SummonerSpellList s = null;
		try {
			s = api.getDataSummonerSpellList();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return s;
	}

	//마스터리정보
	public MasteryList getmastery(){
		MasteryList s = null;
		try {
			s = api.getDataMasteryList();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return s;
	}
	
}

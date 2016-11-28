package pack.model.CurrentGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.CurrentGame.CurrentGameInfo;
import net.rithms.riot.dto.Static.MasteryList;
import net.rithms.riot.dto.Static.SummonerSpellList;
// api 요청과 DB 요청을 받아서 처리하는 Repository
@Repository
public class InGameManager {

	@Autowired
	CurrentGameDao summonerDao;

	@Autowired
	RiotApiManager riotApiManager;

	public Object process(String username) {
		Map<String, Object> map = new HashMap<>();
		Map<String, String> championmap = new HashMap<>();
		Map<String, String> championmap2 = new HashMap<>();
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		List<String> spell_list1 = new ArrayList<>();
		List<String> spell_list2 = new ArrayList<>();
		List<String> spell_list3 = new ArrayList<>();
		List<String> spell_list4 = new ArrayList<>();
		List<Long> icon_list1 = new ArrayList<>();
		List<Long> icon_list2 = new ArrayList<>();
		List<String> summonerName_list1 = new ArrayList<>();
		List<String> summonerName_list2 = new ArrayList<>();
		List<Integer> mastery1 = new ArrayList<>();
		List<Integer> mastery2 = new ArrayList<>();
		List<String> tier1 = new ArrayList<>();
		List<String> tier2 = new ArrayList<>();
		List<String> championName1 = new ArrayList<>();
		List<String> championName2 = new ArrayList<>();
		List<String> gametype = new ArrayList<>();
		try {
//			소환사 이름을 id로 얻음
			Long id = riotApiManager.ApigetSummonerByName(username).getId();
			CurrentGameInfo gameInfo = riotApiManager.ApiGameInfo(id);
//			게임타입
			gametype.add(summonerDao.gameName(gameInfo.getGameQueueConfigId()));
//			스펠정보
			SummonerSpellList spellinfo = riotApiManager.getSummonerSpell();
//			마스터리정보			
			MasteryList masteryinfo = riotApiManager.getmastery();
//			벤챔피언정보 팀구분
			for (int i = 0; i < gameInfo.getBannedChampions().size(); i++) {
				Long chamid = gameInfo.getBannedChampions().get(i).getChampionId();
				if(gameInfo.getBannedChampions().get(i).getTeamId() == 100){
					
					championmap.put("ban" + i, summonerDao.selectchampionKey(chamid));
				}else if(gameInfo.getBannedChampions().get(i).getTeamId() == 200){
					championmap2.put("ban" + i, summonerDao.selectchampionKey(chamid));
				}
				
				
			}
//			레드,블루 팀 나누어서 챔피언,스펠등 데이터 분할
			for (int i = 0; i < gameInfo.getParticipants().size(); i++) {
				Long chamid = gameInfo.getParticipants().get(i).getChampionId();

				long spell = gameInfo.getParticipants().get(i).getSpell1Id();
				long spel2 = gameInfo.getParticipants().get(i).getSpell2Id();
				Long summonerId = gameInfo.getParticipants().get(i).getSummonerId();
				
				if (gameInfo.getParticipants().get(i).getTeamId() == 100) {
					if(summonerDao.tier(summonerId) == null){
						tier1.add("red");
					}else{
						tier1.add(summonerDao.tier(summonerId));
					}
					
					championName1.add(summonerDao.selectchampionName(chamid));
					list1.add(summonerDao.selectchampionKey(chamid));
					spell_list1.add(summonerDao.selectSummonerSpell(spell));
					spell_list2.add(summonerDao.selectSummonerSpell(spel2));

					icon_list1.add(gameInfo.getParticipants().get(i).getProfileIconId());
					summonerName_list1.add(gameInfo.getParticipants().get(i).getSummonerName());
					for (int i2 = 0; i2 < gameInfo.getParticipants().get(i).getMasteries().size(); i2++) {
						int mid = (int) gameInfo.getParticipants().get(i).getMasteries().get(i2).getMasteryId();
						int index = Integer.toString(mid).lastIndexOf("6");
						if (index > 1) {
							mastery1.add(mid);
						}
					}

				} else if (gameInfo.getParticipants().get(i).getTeamId() == 200) {
					if(summonerDao.tier(summonerId) == null){
						tier2.add("blue");
					}else{
						tier2.add(summonerDao.tier(summonerId));
					}
					
					championName2.add(summonerDao.selectchampionName(chamid));
					list2.add(summonerDao.selectchampionKey(chamid));
					spell_list3.add(summonerDao.selectSummonerSpell(spell));
					spell_list4.add(summonerDao.selectSummonerSpell(spel2));

					icon_list2.add(gameInfo.getParticipants().get(i).getProfileIconId());
					summonerName_list2.add(gameInfo.getParticipants().get(i).getSummonerName());
					for (int i2 = 0; i2 < gameInfo.getParticipants().get(i).getMasteries().size(); i2++) {
						int mid = (int) gameInfo.getParticipants().get(i).getMasteries().get(i2).getMasteryId();
						int index = Integer.toString(mid).lastIndexOf("6");
						if (index > 1) {
							mastery2.add(mid);
						}
					}
				}

			}
			
			map.put("masteryinfo", masteryinfo);
			map.put("spellinfo", spellinfo);
			map.put("gameInfo", gameInfo);
			map.put("banChampionName", championmap);
			map.put("banChampionName2", championmap2);
			map.put("summonerSpell1", spell_list1);
			map.put("summonerSpell2", spell_list2);
			map.put("summonerSpell3", spell_list3);
			map.put("summonerSpell4", spell_list4);
			map.put("championKey1", list1);
			map.put("championKey2", list2);
			map.put("icon_list1", icon_list1);
			map.put("icon_list2", icon_list2);
			map.put("summonerName_list1", summonerName_list1);
			map.put("summonerName_list2", summonerName_list2);
			map.put("mastery1", mastery1);
			map.put("mastery2", mastery2);
			map.put("tier1", tier1);
			map.put("tier2", tier2);
			map.put("championName1", championName1);
			map.put("championName2", championName2);
			map.put("gametype", gametype);
			map.put("success", true);
		} catch (RiotApiException e) {
			map.put("success", false);
			map.put("errorCode", e.getErrorCode());
			map.put("errorMessage", e.getMessage());
			return map;
		}
		return map;
	}
}
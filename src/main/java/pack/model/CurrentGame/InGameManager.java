package pack.model.CurrentGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import net.rithms.riot.dto.CurrentGame.CurrentGameInfo;

@Repository
public class InGameManager {

	@Autowired
	CurrentGameDao summonerDao;

	@Autowired
	RiotApiManager riotApiManager;

	public Object process(String username) {
		Map<String, Object> map = new HashMap<>();
		Map<String, String> championmap = new HashMap<>();
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
		List<String> gametype = new ArrayList<>();
		try {
			Long id = riotApiManager.apigetSummonerByName(username).getId();
			CurrentGameInfo gameInfo = riotApiManager.apiGameInfo(id);
			gametype.add(summonerDao.gameName(gameInfo.getGameQueueConfigId()));
			
			for (int i = 0; i < gameInfo.getBannedChampions().size(); i++) {
				Long chamid = gameInfo.getBannedChampions().get(i).getChampionId();
				championmap.put("ban" + i, summonerDao.selectchampionKey(chamid));

			}

			for (int i = 0; i < gameInfo.getParticipants().size(); i++) {
				Long chamid = gameInfo.getParticipants().get(i).getChampionId();
				long spell = gameInfo.getParticipants().get(i).getSpell1Id();
				long spel2 = gameInfo.getParticipants().get(i).getSpell2Id();
				
				if (gameInfo.getParticipants().get(i).getTeamId() == 100) {
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

			map.put("gameInfo", gameInfo);
			map.put("banChampionName", championmap);
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
			map.put("gametype", gametype);
		} catch (Exception e) {
			System.out.println(e);
			map.put("data", "데이터를 가져오는데 실패함.");
			return map;
		}
		return map;
	}
}
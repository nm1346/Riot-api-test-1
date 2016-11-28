package pack.model.most;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Stats.RankedStats;
import pack.model.RiotApiKeyRotate;

//api repository
@Repository
public class MostApiDao {
	@Autowired
	RiotApiKeyRotate api;

	public List<MostDto> apigetMost(long SummonerId) throws RiotApiException {
		List<MostDto> list = null;
		RankedStats rs = api.getRankedStats(SummonerId);
		// most챔피언이 존재하지 않는 경우 제외
		if (rs != null) {
			list = new ArrayList<>();
			for (int i = 0; i < rs.getChampions().size(); i++) {
				if (rs.getChampions().get(i).getId() != 0) {
					MostDto dto = new MostDto();
					dto.setSummonerId(rs.getSummonerId());
					dto.setChampionId(rs.getChampions().get(i).getId());
					dto.setPlayed(rs.getChampions().get(i).getStats().getTotalSessionsPlayed());
					dto.setWin(rs.getChampions().get(i).getStats().getTotalSessionsWon());
					dto.setLose(rs.getChampions().get(i).getStats().getTotalSessionsLost());
					dto.setGoldEarned(rs.getChampions().get(i).getStats().getTotalGoldEarned());
					dto.setMinionkills(rs.getChampions().get(i).getStats().getTotalMinionKills());
					dto.setKills(rs.getChampions().get(i).getStats().getTotalChampionKills());
					dto.setAssists(rs.getChampions().get(i).getStats().getTotalAssists());
					dto.setDeaths(rs.getChampions().get(i).getStats().getTotalDeathsPerSession());
					dto.setMaxKills(rs.getChampions().get(i).getStats().getMaxChampionsKilled());
					dto.setMaxAssists(rs.getChampions().get(i).getStats().getMaxAssists());
					dto.setMaxDeaths(rs.getChampions().get(i).getStats().getMaxNumDeaths());
					dto.setDamageDealt(rs.getChampions().get(i).getStats().getTotalDamageDealt());
					dto.setDamageTaken(rs.getChampions().get(i).getStats().getTotalDamageTaken());
					dto.setDoubleKills(rs.getChampions().get(i).getStats().getTotalDoubleKills());
					dto.setTripleKills(rs.getChampions().get(i).getStats().getTotalTripleKills());
					dto.setQuadraKills(rs.getChampions().get(i).getStats().getTotalQuadraKills());
					dto.setPentaKills(rs.getChampions().get(i).getStats().getTotalPentaKills());
					list.add(dto);
				}
			}
		}
		return list;
	}
}

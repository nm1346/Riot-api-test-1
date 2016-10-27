package pack.model.recentgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Game.RecentGames;
import pack.model.RiotApiKeyRotate;

@Repository
public class RecentApiDao {
	@Autowired
	RiotApiKeyRotate api;
	
	public RecentGames getRecentGame(Long id) throws RiotApiException{
		return api.getRecentGames(id);
	}
	
	
	
}

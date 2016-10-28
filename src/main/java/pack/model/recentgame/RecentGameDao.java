package pack.model.recentgame;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.Player;
import net.rithms.riot.dto.Game.RawStats;

@Repository
public class RecentGameDao {
	@Autowired
	RecentGameDBInter gameDBInter;
	/*
	 * @Autowired RecentApiDao apiDao; String[] summonerIds; summonerIds[i] =
	 * Long.toString(playerlist.get(i).getSummonerId()); summonerIds = new
	 * String[game.size() * game.get(0).getFellowPlayers().size()]; 이름 뽑기 보류.
	 */
	public boolean insertRecentGame(Long summonerId, List<Game> game) {
		boolean b = false;
		try {
			for (int i = 0; i < game.size(); i++) {
				if (gameDBInter.checkGame(game.get(i).getGameId()) == null){
					gameDBInter.insertGame(summonerId, game.get(i));
					insertPlayer(game.get(i).getGameId(), game.get(i).getFellowPlayers());
					insertRawstats(game.get(i).getGameId(), game.get(i).getStats());
				}else{
					continue;
				}
			}
			b = true;
		} catch (Exception e) {
			b = false;
			System.out.println("error : " + e);
		}
		return b;
	}
	
	
	
	public void insertPlayer(Long gameId, List<Player> playerlist) throws DataAccessException {
		for (int i = 0; i < playerlist.size(); i++) {
			gameDBInter.insertFellowPlayers(gameId, playerlist.get(i));
		}
	}
	
	
	public void insertRawstats(Long gameId, RawStats rawStats) throws DataAccessException {
		gameDBInter.insertRawStats(gameId, rawStats);
	}
	
	
	public List<GameDto> selectRecentGames(Long summonerId) throws DataAccessException {
		return gameDBInter.selectRecentGames(summonerId);
	}
	
	
	public List<PlayerDto> selectFellowPlayer(Long gameId) throws DataAccessException {
		return gameDBInter.selectFellowPlayers(gameId);
	}
	
	public RawStats selectRawstats(Long gameId) throws DataAccessException {
		return gameDBInter.selectRawstats(gameId);
	}
}

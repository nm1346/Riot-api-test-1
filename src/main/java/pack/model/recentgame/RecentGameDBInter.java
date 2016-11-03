package pack.model.recentgame;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.Player;
import net.rithms.riot.dto.Game.RawStats;

public interface RecentGameDBInter {
	@Select("select gameId from recentgames where gameId=#{gameId} ")
	public Long checkGame(@Param("gameId")Long gameId);
	
	@Select("select * from rawstats where gameId=#{gameId} ")
	public RawStats selectRawstats(@Param("gameId")Long gameId);
	
	@Select("select gameId, summonerId, teamId , championId ,"
			+ "kee as chamName from fellowplayers inner join champion on championId = id where gameId=#{gameId};")
	public List<PlayerDto> selectFellowPlayers(@Param("gameId")Long gameId);

	@Select("select summonerId , gameId, gameMode , gameType , invalid, ipEarned , createDate, "
			+ "championId , mapId , spell1 , spell2 , subType , teamId, kee as chamName " 
			+ "from recentgames inner join champion on championId = id "
			+ "where summonerId = #{summonerId} ORDER by gameId desc LIMIT 0 ,10;")
	public List<GameDto> selectRecentGames(@Param("summonerId")Long summonerId);
	
	@Insert("insert into RecentGames values(#{summonerId},#{game.gameId},"
			+ "#{game.gameMode},#{game.gameType},#{game.invalid},#{game.level},#{game.ipEarned},"
			+ "#{game.createDate},#{game.championId},"
			+ "#{game.mapId},#{game.spell1},#{game.spell2},#{game.subType},#{game.teamId})")
	public boolean insertGame(@Param("summonerId")Long summonerId,@Param("game")Game game);
	
	@Insert("insert into fellowplayers values(#{gameId},"
			+ "#{player.summonerId},#{player.teamId},#{player.championId})")
	public boolean insertFellowPlayers(@Param("gameId")Long gameId,@Param("player")Player player);
	
	@Insert("insert into rawstats values(#{gameId},"
			+ "#{stats.assists},#{stats.barracksKilled},#{stats.championsKilled},"
			+ "#{stats.combatPlayerScore},#{stats.consumablesPurchased},"
			+ "#{stats.damageDealtPlayer},#{stats.doubleKills},#{stats.firstBlood},#{stats.gold},#{stats.goldEarned},"
			+ "#{stats.goldSpent},#{stats.item0},#{stats.item1},#{stats.item2},#{stats.item3},"
			+ "#{stats.item4},#{stats.item5},#{stats.item6},#{stats.itemsPurchased},#{stats.killingSprees},"
			+ "#{stats.largestCriticalStrike},#{stats.largestKillingSpree},#{stats.largestMultiKill},"
			+ "#{stats.legendaryItemsCreated},#{stats.level},#{stats.magicDamageDealtPlayer},"
			+ "#{stats.magicDamageDealtToChampions},#{stats.magicDamageTaken},#{stats.minionsDenied},#{stats.minionsKilled},"
			+ "#{stats.neutralMinionsKilled},#{stats.neutralMinionsKilledEnemyJungle},"
			+ "#{stats.neutralMinionsKilledYourJungle},#{stats.nexusKilled},#{stats.nodeCapture},"
			+ "#{stats.nodeCaptureAssist},#{stats.nodeNeutralize},#{stats.nodeNeutralizeAssist},#{stats.numDeaths},"
			+ "#{stats.numItemsBought},#{stats.objectivePlayerScore},#{stats.pentaKills},#{stats.physicalDamageDealtPlayer},"
			+ "#{stats.physicalDamageDealtToChampions},#{stats.physicalDamageTaken},"
			+ "#{stats.quadraKills},#{stats.sightWardsBought},#{stats.spell1Cast},#{stats.spell2Cast},"
			+ "#{stats.spell3Cast},#{stats.spell4Cast},#{stats.summonSpell1Cast},#{stats.summonSpell2Cast},"
			+ "#{stats.superMonsterKilled},#{stats.team},#{stats.teamObjective},#{stats.timePlayed},"
			+ "#{stats.totalDamageDealt},#{stats.totalDamageDealtToChampions},#{stats.totalDamageTaken},"
			+ "#{stats.totalHeal},#{stats.totalPlayerScore},#{stats.totalScoreRank},#{stats.totalTimeCrowdControlDealt},"
			+ "#{stats.totalUnitsHealed},#{stats.tripleKills},#{stats.trueDamageDealtPlayer},"
			+ "#{stats.trueDamageDealtToChampions},#{stats.trueDamageTaken},#{stats.turretsKilled},"
			+ "#{stats.unrealKills},#{stats.victoryPointTotal},#{stats.visionWardsBought},#{stats.wardKilled},"
			+ "#{stats.wardPlaced},#{stats.win})")
	public boolean insertRawStats(@Param("gameId")Long gameId,@Param("stats")RawStats player);
}

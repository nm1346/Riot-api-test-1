package pack.model.match;



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;



public interface MatchDBInter {
	@Select("select banChampionId1, banChampionId2, banChampionId3, neutralMinionsKilled, neutralMinionsKilledTeamjungle, neutralMinionsKilledEnemyjungle, matchteam.matchId as matchId, matchteam.teamId as teamId, baronkills, dragonkills, towerkills, spell1Id, spell2Id, championId, champion.name as championNameK, champion.kee as championNameE, mastery, kills, deaths, assists, item0, item1, item2, item3, item4, item5, totalDamageDealt, totalDamageTaken, wardsPlaced, wardsKilled, minionskilled, goldEarned, matchparticipant.participantId as participantId, summonerId, summonerName from matchparticipant inner join matchparticipantIdentities on matchparticipant.participantId = matchparticipantIdentities.participantId inner join matchteam on matchparticipant.teamId = matchteam.teamId left join champion on matchparticipant.championId = champion.id where matchteam.matchId=#{matchId} and matchparticipant.matchId=#{matchId} and matchparticipantidentities.matchId=#{matchId}")
	public List<MatchDto> getMatch(long matchId);
	
	@Insert("insert into matchteam(banChampionId1, banChampionId2, banChampionId3, matchId, teamId, baronkills, dragonkills, towerkills) values(#{banChampionId1}, #{banChampionId2}, #{banChampionId3}, #{matchId},#{teamId},#{baronkills},#{dragonkills},#{towerkills})")
	public int insertMatchTeam(MatchTeamDto dto);
	
	@Insert("insert into matchparticipant(neutralMinionsKilled, neutralMinionsKilledTeamjungle, neutralMinionsKilledEnemyjungle, matchId, teamId, participantId, spell1Id, spell2Id, championId, mastery, kills, deaths, assists, item0, item1, item2, item3, item4, item5, totalDamageDealt, totalDamageTaken, wardsPlaced, wardsKilled, minionskilled, goldEarned) values(#{neutralMinionsKilled}, #{neutralMinionsKilledTeamjungle}, #{neutralMinionsKilledEnemyjungle}, #{matchId}, #{teamId}, #{participantId}, #{spell1Id}, #{spell2Id}, #{championId}, #{mastery}, #{kills}, #{deaths}, #{assists}, #{item0}, #{item1}, #{item2}, #{item3}, #{item4}, #{item5}, #{totalDamageDealt}, #{totalDamageTaken}, #{wardsPlaced}, #{wardsKilled}, #{minionskilled}, #{goldEarned})")
	public int insertMatchParticipant(MatchParticipantDto dto);
	
	@Insert("insert into matchparticipantIdentities(matchId, participantId, summonerId, summonerName) values(#{matchId}, #{participantId}, #{summonerId}, #{summonerName})")
	public int insertMatchParticipantIdentities(MatchParticipantIdentitiesDto dto);
}

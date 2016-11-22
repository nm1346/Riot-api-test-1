package pack.model.featuredgames;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import net.rithms.riot.dto.League.League;
import pack.model.summoner.LeagueDto;

public interface FeaturedDBInter {
	@Select({"<script>"
			+ "select summoner.name as name,"
			+ " tier,"
			+ " division,wins,losses,leaguePoints"
			+ " from league inner join summoner using(id) "
			+ " inner join entries on summoner.id=entries.playerOrTeamId "
			+ " where summoner.name in"
			+ " <foreach item='name' index='index' collection='list'"
			+ " open='(' separator=',' close=')'>"
			+ " #{name}"
			+ " </foreach></script>"})
	public List<EntryDto> selectLeagueSummoner(@Param("list")ArrayList<String> list);
}

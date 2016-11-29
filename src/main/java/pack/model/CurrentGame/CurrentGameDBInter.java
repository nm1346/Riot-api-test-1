package pack.model.CurrentGame;

import org.apache.ibatis.annotations.Select;

//Db인터페이스
public interface CurrentGameDBInter {
	
	@Select("select name from champion where id=#{id}")
	public String selectChampion(Long id);
	
	@Select("select kee from champion where id=#{id}")
	public String selectChampionKey(Long id);
	
	@Select("select kee from summonerspell where id=#{id}")
	public String selectSummonerSpell(Long id);
	
	@Select("select name from gamename where queueType=#{queueType}")
	public String gamename(Long queueType);
	
	@Select("select tier from league where id=#{id}")
	public String Tier(Long id);
	
	
}

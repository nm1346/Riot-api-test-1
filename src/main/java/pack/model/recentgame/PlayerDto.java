package pack.model.recentgame;



public class PlayerDto {
	private Long gameId,summonerId;
	private int teamId,championId;
	private String chamName;
	public String getChamName() {
		return chamName;
	}
	public void setChamName(String chamName) {
		this.chamName = chamName;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Long getSummonerId() {
		return summonerId;
	}
	public void setSummonerId(Long summonerId) {
		this.summonerId = summonerId;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getChampionId() {
		return championId;
	}
	public void setChampionId(int championId) {
		this.championId = championId;
	}
}

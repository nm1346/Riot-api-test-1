package pack.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.model.RecentGameManager;
//{"id":1315619,"revisionDate":1472390698000,"summonerLevel":30,"profileIconId":0,"name":"서폿오알정글"}
import pack.model.SummonerManager;


@CrossOrigin(origins="*")
@Controller
public class TestController {
	//aggregatedStats
//	RiotApi api = new RiotApi("RGAPI-318533dd-c968-4019-8f0b-7d9ead363547", Region.KR);
//	PlayerStatsSummaryList summary = api.getPlayerStatsSummary(1315619);
//	
	@Autowired
	SummonerManager summonerManager;
	@Autowired
	RecentGameManager gameManager;
	

	@RequestMapping("user/{username}")
	@ResponseBody
	public Object getlist1(@PathVariable("username")String username) {
		SummonerBean bean = new SummonerBean();
		bean.setName(username);
		return summonerManager.getSummonerAndLeague(bean);
	}
	/*@RequestMapping("game/{username}")
	@ResponseBody
	public Object getRecentgames(@PathVariable("username")String username) {
		SummonerBean bean = new SummonerBean();
		bean.setName(username);
		return gameManager.getRecentGames(bean);
	}*/
	
}

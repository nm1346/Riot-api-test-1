package pack.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.PlatformId;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.ChampionMastery.ChampionMastery;
import net.rithms.riot.dto.MatchList.MatchList;
import net.rithms.riot.dto.Stats.PlayerStatsSummary;
import net.rithms.riot.dto.Stats.PlayerStatsSummaryList;
import net.rithms.riot.dto.Summoner.Summoner;
//{"id":1315619,"revisionDate":1472390698000,"summonerLevel":30,"profileIconId":0,"name":"서폿오알정글"}
import pack.model.SummonerManager;
import pack.model.summoner.SummonerDao;

@CrossOrigin(origins="*")
@Controller
public class SearchController {
	
	@Autowired
	SummonerManager summonerManager;
	

	@RequestMapping("user/{username}")
	@ResponseBody
	public Object getlist1(@PathVariable("username")String username) {
		SummonerBean bean = new SummonerBean();
		bean.setName(username);
		return summonerManager.getSummonerAndLeague(bean);
	}
	
}

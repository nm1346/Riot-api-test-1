package pack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pack.model.ChallengerManager;
import pack.model.MatchManager;
import pack.model.SummonerManager;

@CrossOrigin(origins="*")
@RestController
public class SearchController {
	
	@Autowired
	SummonerManager summonerManager;
	@Autowired
	ChallengerManager challengerManager; 
	@Autowired
	MatchManager matchManager;
	
	@RequestMapping("user/{username}")
	@ResponseBody
	public Object getlist1(@PathVariable("username")String username) {
		SummonerBean bean = new SummonerBean();
		bean.setName(username);
		return summonerManager.getSummonerAndLeague(bean);
	}
	
	@RequestMapping("challenger")
	@ResponseBody
	public Object getChallenger(){
		return challengerManager.getChallenger();
	}
	
	@RequestMapping("master")
	@ResponseBody
	public Object getMaster(){
		return challengerManager.getMaster();
	}
	
	@RequestMapping("match/{matchId}")
	@ResponseBody
	public Object getMatch(@PathVariable("matchId") long matchId){
		return matchManager.getMatch(matchId);
	}
	
}

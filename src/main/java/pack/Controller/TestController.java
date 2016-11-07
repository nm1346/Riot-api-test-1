package pack.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//{"id":1315619,"revisionDate":1472390698000,"summonerLevel":30,"profileIconId":0,"name":"서폿오알정글"}
import pack.model.SummonerManager;


@CrossOrigin(origins="*")
@Controller
public class TestController {
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

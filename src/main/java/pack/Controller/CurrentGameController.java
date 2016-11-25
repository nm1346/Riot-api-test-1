package pack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pack.model.CurrentGame.InGameManager;


@CrossOrigin(origins="*")
@RestController
public class CurrentGameController {
	
	@Autowired
	InGameManager inGameManager;
	
	@RequestMapping("/currentGame/{username}")
	@ResponseBody
	public Object getlist1(@PathVariable("username")String username) {
		Object object = new Object();
//		인게임매니저에서 검색한 소환사이름을 인자로 데이터를 받아옴
		object = inGameManager.process(username);
		return object;
	}
	
}

package pack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pack.model.RecentchampManager;


@CrossOrigin(origins="*")
@RestController
public class RecentchampController {
	
	
	@Autowired
	RecentchampManager champmanager;
	
	@RequestMapping("/recentchamp/{summonerId}")
	@ResponseBody
	public Object getlist1(@PathVariable("summonerId")Long SummonerId) {
		System.out.println(SummonerId);
		Object object = new Object();
		object = champmanager.getRecentchamplist(SummonerId);
		return object;
	}
}

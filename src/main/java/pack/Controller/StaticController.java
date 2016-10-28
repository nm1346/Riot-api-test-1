package pack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pack.staticData.staticApiManager;

@CrossOrigin("*")
@RestController
public class StaticController {
	@Autowired
	staticApiManager staticApiManager;
	
	@RequestMapping("static/champion")
	@ResponseBody
	public Object getChampionList(){
		return staticApiManager.getChampionList();
	}
	@RequestMapping("static/champion/{champid}")
	@ResponseBody
	public Object getChampion(@RequestParam int champid){
		return staticApiManager.getChampion(champid);
	}
	
	
	
}

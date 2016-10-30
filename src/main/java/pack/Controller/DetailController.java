package pack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionList;
import pack.model.StaticManager;
import pack.model.staticdata.StaticApiDao;





@CrossOrigin("*")
@RestController
public class DetailController {
	
	@Autowired
	StaticManager staticManager;
	
	
	@RequestMapping("/static/locale/{locale}")
	@ResponseBody
	public Object getlocalelist(@PathVariable String locale){
		
		return staticManager.getLocaleList(locale);
	}
	
	
	@RequestMapping("/static/champion")
	@ResponseBody
	public Object getChampionList(){
		ChampionList dto=null;
		return dto;
	}
	@RequestMapping("/static/champion/{id}")
	@ResponseBody
	public Object getChampion(@PathVariable("id")int id){
		
		Champion dto=null;
		return dto;
	}
}

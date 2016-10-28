package pack.staticData;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionList;
import pack.model.RiotApiKeyRotate;


//API 제한조건 없으므로 AOP X
@Component
public class staticApiManager {
	@Autowired
	RiotApiKeyRotate api;
	
	public Map<String,Object> getChampionList(){
		HashMap<String,Object> map=new HashMap<>();
		ChampionList list=null;
		try {
			list=api.getDataChampionList();
			map.put("championList", list);
			map.put("success", "true");
		} catch (RiotApiException e) {
			map.put("success", "false");
			map.put("error",e.getMessage());
			map.put("errorCode", e.getErrorCode());
		}
		return map;
	}
	public Map<String,Object> getChampion(int championId){
		HashMap<String,Object> map=new HashMap<>();
		Champion dto=null;
		try {
			dto=api.getDataChampion(championId);
			map.put("champion", dto);
			map.put("success", "true");
		} catch (RiotApiException e) {
			map.put("success", "false");
			map.put("error",e.getMessage());
			map.put("errorCode", e.getErrorCode());
		}
		return map;
	}
}

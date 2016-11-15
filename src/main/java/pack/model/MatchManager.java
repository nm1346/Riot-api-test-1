package pack.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.rithms.riot.api.RiotApiException;
import pack.model.match.AvgDto;
import pack.model.match.MatchApiDao;
import pack.model.match.MatchDao;
import pack.model.match.MatchDto;
import pack.model.match.SpellDto;
import pack.model.match.MatchBean;

@Service
public class MatchManager {
	
	@Autowired
	MatchApiDao apiDao; // api
	@Autowired
	MatchDao matchDao; // db
	
	public Map<String, Object> getMatch(long matchId) {
		HashMap<String, Object> map = new HashMap<>();	
		
		List<MatchDto> list = matchDao.selectMatch(matchId);
		if(list.size()>5){
			System.out.println("DB");
			List<AvgDto> list1 = matchDao.selectAvg();
			List<SpellDto> dto = matchDao.getspell();
			map.put("spell", dto);
			map.put("match", list);
			map.put("success", "true");
			map.put("success", "true");
			map.put("avg", list1);
			return map;   
		}
		try {
			System.out.println("API");
			MatchBean bean = new MatchBean();
			bean = apiDao.apigetMatch(matchId);
			matchDao.insertMatch(bean);
			list = matchDao.selectMatch(matchId);
			map.put("success", "true");
			map.put("match", list);
			List<AvgDto> list1 = matchDao.selectAvg();
			map.put("success", "true");
			map.put("avg", list1);
			List<SpellDto> dto = matchDao.getspell();
			map.put("spell", dto);
		} catch (RiotApiException e) {
			System.out.println("getMatch err " + e);
			map.put("success", "false");
			map.put("error", e.getMessage());
			map.put("errorCode", e.getErrorCode());
		}		
		return map;
	}
	
	public Map<String, Object> getAvg(long matchId) {
		HashMap<String, Object> map = new HashMap<>();	
		
		try {
			MatchBean bean = new MatchBean();
			bean = apiDao.apigetMatch(matchId);
			//추가 insert 안함~
			matchDao.insertAvg(bean);
			List<AvgDto> list = matchDao.selectAvg();
			map.put("success", "true");
			map.put("avg", list);
		} catch (RiotApiException e) {
			System.out.println("getMatch err " + e);
			map.put("success", "false");
			map.put("error", e.getMessage());
			map.put("errorCode", e.getErrorCode());
		}		
		return map;
	}
	
	public Map<String, Object> getRealAvg() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<AvgDto> list1 = matchDao.selectAvg();
			map.put("success", "true");
			map.put("avg", list1);
		} catch (Exception e) {
			map.put("success", "false");
			map.put("error", e.getMessage());
		}
		return map;
	}
	
}

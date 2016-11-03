package pack.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.rithms.riot.api.RiotApiException;
import pack.Controller.SummonerBean;
import pack.model.summoner.LeagueDto;
import pack.model.summoner.SummonerApiDao;
import pack.model.summoner.SummonerDao;
import pack.model.summoner.SummonerDto;

@Service
public class SummonerManager {
	
	@Autowired
	SummonerApiDao apiDao;		//api
	@Autowired
	SummonerDao summonerDao;	//db
	
	//성공했을시 success=true,leagueData,summonerData
	//에러시 success=false,error,errorCode
	public Map<String,Object> getSummonerAndLeague(SummonerBean bean){
		HashMap<String,Object> map=new HashMap<>();
		SummonerDto summoner =summonerDao.selectSummoner(bean);
		LeagueDto dto=null;
		if(summoner!=null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date date=null;
			try {
				date=format.parse(summoner.getSearchDate());
				summoner.setSearchDate(format.format(date));
			} catch (Exception e) {
				
			}
			Calendar searchDate=Calendar.getInstance();
			searchDate.setTime(date);
			searchDate.add(Calendar.MINUTE, 2);
			if(searchDate.getTime().before(new Date())){
				try {
					summoner=apiDao.ApigetSummonerByName(bean.getName());
					dto=apiDao.ApigetLeagueData(summoner.getId());
					summonerDao.updateSummoner(dto, summoner);
				} catch (RiotApiException e) {
					System.out.println("getSummonerAndLeague ApiGetUpdate Error"+e);
					map.put("success", "false");
					map.put("error", e.getMessage());
					map.put("errorCode", e.getErrorCode());
					return map;
				}
			}
			map.put("summonerData", summoner);
			map.put("leagueData", summonerDao.selectLeagueData(summoner.getId()));
			map.put("success", "true");
		}else{
			try {
				summoner=apiDao.ApigetSummonerByName(bean.getName());
				dto=apiDao.ApigetLeagueData(summoner.getId());
				summonerDao.insertSummoner(dto, summoner);

				map.put("summonerData", summoner);
				map.put("leagueData", summonerDao.selectLeagueData(summoner.getId()));
				map.put("success", "true");
			} catch (RiotApiException e) {
				map.put("success", "false");
				map.put("error", e.getMessage());
				map.put("errorCode", e.getErrorCode());
			}
			
		}
		return map;
	}
}

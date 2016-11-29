package pack.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.rithms.riot.api.RiotApiException;
import pack.model.most.MostApiDao;
import pack.model.most.MostDao;
import pack.model.most.MostDto;

@Service
public class MostManager {
	@Autowired
	MostApiDao apiDao;
	@Autowired
	MostDao mostDao;

	public Map<String, Object> getMost(long summonerId) {
		HashMap<String, Object> map = new HashMap<>();

		// 하루단위로 most 챔피언 갱신
		MostDto most = mostDao.getMost(summonerId).get(0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date = null;
		try {
			date = format.parse(most.getSearchDate());
			most.setSearchDate(format.format(date));
		} catch (Exception e) {

		}
		Calendar searchDate = Calendar.getInstance();
		searchDate.setTime(date);
		searchDate.add(Calendar.DAY_OF_MONTH, 1);

		if (searchDate.getTime().before(new Date())) {
			// 하루가 지나면 api 참조
			try {
				List<MostDto> list = apiDao.ApigetMost(summonerId);
				mostDao.insertMost(list);
				map.put("most", list);
				map.put("success", "true");
			} catch (RiotApiException e) {
				System.out.println("getChallenger" + e);
				map.put("success", "false");
				map.put("error", e.getMessage());
				map.put("errorCode", e.getErrorCode());
			}
		} else {
			// db
			List<MostDto> list = mostDao.getMost(summonerId);
			map.put("entry", list);
			map.put("success", "true");
		}
		return map;
	}
}

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
import pack.model.challenger.ChallengerApiDao;
import pack.model.challenger.ChallengerDao;
import pack.model.challenger.ChallengerDto;

@Service
public class ChallengerManager {

	@Autowired
	ChallengerApiDao apiDao; // api
	@Autowired
	ChallengerDao challengerDao; // db

	public Map<String, Object> getChallenger() {

		HashMap<String, Object> map = new HashMap<>();

		// 하루단위로 랭킹 갱신
		ChallengerDto challenger = challengerDao.getChallenger().get(0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date = null;
		try {
			date = format.parse(challenger.getSearchDate());
			challenger.setSearchDate(format.format(date));
		} catch (Exception e) {

		}
		Calendar searchDate = Calendar.getInstance();
		searchDate.setTime(date);
		searchDate.add(Calendar.DAY_OF_MONTH, 1);

		if (searchDate.getTime().before(new Date())) {
			// 하루가 지나면 api 참조
			try {

				List<ChallengerDto> list = apiDao.apigetChallenger();
				challengerDao.insertChallenger(list);
				List<ChallengerDto> mlist = apiDao.apigetMaster();
				challengerDao.insertMaster(mlist);
				map.put("challenger", list);
				map.put("master", mlist);
				map.put("success", "true");
			} catch (RiotApiException e) {
				System.out.println("getChallenger" + e);
				map.put("success", "false");
				map.put("error", e.getMessage());
				map.put("errorCode", e.getErrorCode());
			}

		} else {
			// db
			List<ChallengerDto> list = challengerDao.getChallenger();
			map.put("entry", list);
			map.put("success", "true");
		}

		return map;
	}
}

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
	MatchApiDao apiDao;
	@Autowired
	MatchDao matchDao;

	public Map<String, Object> getMatch(long matchId) {
		HashMap<String, Object> map = new HashMap<>();

		// match의 경우 수정할 필요가 없는 정보이기 때문에 존재하면 무조건 DB에서 출력
		List<MatchDto> list = matchDao.selectMatch(matchId);
		// DB에 존재하는 경우
		if (list.size() > 5) {
			List<AvgDto> list1 = matchDao.selectAvg();
			List<SpellDto> dto = matchDao.getspell();
			map.put("spell", dto);
			map.put("match", list);
			map.put("avg", list1);
			map.put("success", "true");
			return map;
		}
		// DB에 존재하지 않는 경우 api 참조
		try {
			MatchBean bean = new MatchBean();
			bean = apiDao.apigetMatch(matchId);
			matchDao.insertMatch(bean);
			list = matchDao.selectMatch(matchId);
			List<AvgDto> list1 = matchDao.selectAvg();
			List<SpellDto> dto = matchDao.getspell();
			map.put("match", list);
			map.put("avg", list1);
			map.put("spell", dto);
			map.put("success", "true");
		} catch (RiotApiException e) {
			System.out.println("getMatch err " + e);
			map.put("success", "false");
			map.put("error", e.getMessage());
			map.put("errorCode", e.getErrorCode());
		}
		return map;
	}

	// DB(신빙성) 부족으로 인한 avg insert용
	public Map<String, Object> getAvg(long matchId) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			MatchBean bean = new MatchBean();
			bean = apiDao.apigetMatch(matchId);
			// 추가 insert 안함~
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
}

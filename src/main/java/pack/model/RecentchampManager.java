package pack.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.recentgame.RecentGameDao;
import pack.model.recentgame.RecentchamDto;

@Service
public class RecentchampManager {
	@Autowired
	RecentGameDao gameDao;

	public Map<String, Object> getRecentchamplist(Long SummonerId) {
		Map<String, Object> champmap = new HashMap<>();
		try {
			List<RecentchamDto> champlist = gameDao.getrecentChamplist(SummonerId);
			if (champlist == null) {
				champmap.put("champlist", "조회된 영웅은 존재하지 않습니다.");
				champmap.put("Success", 1);
				return champmap;
			}
			champmap.put("champlist", champlist);
			champmap.put("Success", 1);
		} catch (Exception e) {
			champmap.put("error", e.getMessage());
			champmap.put("Success", 0);
		}
		return champmap;
	}
}

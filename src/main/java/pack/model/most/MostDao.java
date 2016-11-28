package pack.model.most;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//db repository
@Repository
public class MostDao {
	@Autowired
	MostDBInter mostDBInter;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public boolean insertMost(List<MostDto> list) throws DataAccessException {
		boolean success = false;
		try {
			// most는 update 보다 delete가 용이함
			mostDBInter.deleteMost(list.get(0).getSummonerId());
			for (int i = 0; i < list.size(); i++) {
				mostDBInter.insertMost(list.get(i));
			}
			success = true;

		} catch (Exception e) {
			return success;
		}
		return success;
	}

	// most 챔피언
	public List<MostDto> getMost(long summonerId) {
		return mostDBInter.selectMost(summonerId);
	}

	// wost 챔피언
	public List<MostDto> getWorstList(long summonerId) {
		return mostDBInter.selectMorstWost(summonerId);
	}
}

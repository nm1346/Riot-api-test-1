package pack.model.most;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.rithms.riot.api.RiotApiException;

@Repository
public class MostDao {
	@Autowired
	MostDBInter mostDBInter;
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public boolean insertMost(List<MostDto> list) throws DataAccessException {
		boolean success = false;
		try {
			//처음만 사용하고 삭제
			for (int i = 0; i < list.size(); i++) {
				mostDBInter.insertMost(list.get(i));
			}
			success = true;
			mostDBInter.deleteMost(list.get(0).getSummonerId());
			//두번째부터
			
				for (int i = 0; i < list.size(); i++) {
					mostDBInter.insertMost(list.get(i));
				}
				success = true;
			
		} catch (Exception e) {
			return success;
		}
		return success;
	}
	
	public List<MostDto> getMost(long summonerId){
		return mostDBInter.selectMost(summonerId);
	}
}

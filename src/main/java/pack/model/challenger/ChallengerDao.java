package pack.model.challenger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//db repository
@Repository
public class ChallengerDao {
	@Autowired
	private ChallengerDBInter challengerDBInter;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public boolean insertChallenger(List<ChallengerDto> list) {
		boolean success = false;
		try {
			// 랭크 순위는 update가 아닌 delete가 용이함
			challengerDBInter.deleteChallenger();
			for (int i = 0; i < list.size(); i++) {
				challengerDBInter.insertChallenger(list.get(i));
			}
			success = true;
		} catch (Exception e) {
			return success;
		}
		return success;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public boolean insertMaster(List<ChallengerDto> list) {
		boolean success = false;
		try {
			// 랭크 순위는 update가 아닌 delete가 용이함
			challengerDBInter.deleteMaster();
			for (int i = 0; i < list.size(); i++) {
				challengerDBInter.insertMaster(list.get(i));
			}
			success = true;
		} catch (Exception e) {
			return success;
		}
		return success;
	}

	public List<ChallengerDto> getChallenger() {
		return challengerDBInter.selectChallenger();
	}

	public List<ChallengerDto> getMaster() {
		return challengerDBInter.selectMaster();
	}
}

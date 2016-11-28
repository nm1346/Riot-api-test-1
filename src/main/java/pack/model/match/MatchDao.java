package pack.model.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//db repository
@Repository
public class MatchDao {
	@Autowired
	private MatchDBInter matchDBInter;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public boolean insertMatch(MatchBean bean) {
		boolean success = false;
		try {
			// match로 넘어오는 team정보와 match정보 배열의 크기가 다르기 때문에 나눠서 insert
			for (int i = 0; i < bean.getTeam().size(); i++) {
				matchDBInter.insertMatchTeam(bean.getTeam().get(i));
			}
			for (int i = 0; i < bean.getParticipant().size(); i++) {
				matchDBInter.insertMatchParticipantIdentities(bean.getParticipantIdentities().get(i));
				matchDBInter.insertMatchParticipant(bean.getParticipant().get(i));
			}
			success = true;
		} catch (Exception e) {
			return success;
		}
		return success;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public boolean insertAvg(MatchBean bean) {
		boolean success = false;
		try {
			// avg insert
			for (int i = 0; i < bean.getParticipant().size(); i++) {
				matchDBInter.insertAvg(bean.getParticipant().get(i));
			}
			success = true;
		} catch (Exception e) {
			return success;
		}
		return success;
	}

	public List<MatchDto> selectMatch(long matchId) {
		return matchDBInter.getMatch(matchId);
	}

	public List<AvgDto> selectAvg() {
		return matchDBInter.getAvg();
	}

	public List<SpellDto> getspell() {
		return matchDBInter.getSpell();
	}

}

package pack.model.staticdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.PlatformId;
import net.rithms.riot.constant.Region;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionList;
import net.rithms.riot.dto.Static.LanguageStrings;
import net.rithms.riot.dto.Static.LanguageStringsData;
import net.rithms.riot.dto.Static.Realm;
import pack.model.RiotApiKeyRotate;

@Repository
public class StaticApiDao {
	
	@Autowired
	RiotApiKeyRotate api;
	
	//버전리스트 반환
	public List<String> getStaticVersion(){
		List<String> list=null;
		try {
			list=api.getDataVersions();
		} catch (Exception e) {
			return null;
		}
		return list;
	}
	//각 지역의 언어locale문자값 반환 api
		public List<String> getStaticLaguageList(){
			List<String> list=null;
			try {
				list=api.getDataLanguages();
			} catch (Exception e) {
				return null;
			}
			return list;
		}
	//해당지역의 언어에 따른 key=영어값 value=지역에 맞춘 값들
	public LanguageStrings getStaticLanguages(String locale,String version){
		LanguageStrings list=null;
		try {
			list=api.getDataLanguageStrings(locale, version);
		} catch (Exception e) {
			return null;
		}
		return list;
	}
	
	//static 데이터 cdn주소및 데이터의 버전 반환
	public Realm getStaticRealm(){
		Realm realm=null;
		try {
			realm=api.getDataRealm();
		} catch (Exception e) {
			return null;
		}
		return realm;
	}
	
	
	
	
}

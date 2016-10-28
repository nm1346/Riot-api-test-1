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
import pack.model.RiotApiKeyRotate;

@Repository
public class StaticApiDao {
	
	@Autowired
	RiotApiKeyRotate api;
	
	public List<String> getStaticVersion(){
		List<String> list=null;
		try {
			list=api.getDataVersions();
		} catch (Exception e) {
			return null;
		}
		return list;
	}
	public ChampionList getStaticChampionlist(){
		ChampionList list=null;
		try {
			list=api.getDataChampionList();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;

	}
	public Champion getStaticChampion(int id,String version){
		List<String> list=getStaticVersion();
		Champion dto=null;
		try {
			dto=api.getDataChampion(id,"ko_KR",list.get(0), ChampData.ALL);
		} catch (RiotApiException e) {
			System.out.println(e);
		}
		return dto;

	}
	
	
}

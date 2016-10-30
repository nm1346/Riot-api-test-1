package pack.model.staticdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionList;
import pack.model.RiotApiKeyRotate;

@Repository
public class StaticChampionApiDao {
	@Autowired
	RiotApiKeyRotate api;
	
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
		Champion dto=null;
		try {
			dto=api.getDataChampion(id,"en_US",version, ChampData.ALL);
		} catch (RiotApiException e) {
			System.out.println(e);
		}
		return dto;

	}
}

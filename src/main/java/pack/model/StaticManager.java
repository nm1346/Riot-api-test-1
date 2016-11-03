package pack.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pack.model.staticdata.StaticApiDao;

@Component
public class StaticManager {
	@Autowired
	StaticApiDao staticApiDao;
	
	public Map<String,Object> getLocaleList(String locale){
		Map<String, Object> map=new HashMap<>();
		
		map.put("languagelist", staticApiDao.getStaticLaguageList());
		map.put("language", staticApiDao.getStaticLanguages(locale, staticApiDao.getStaticRealm().getV()));
		return map;
	}
}

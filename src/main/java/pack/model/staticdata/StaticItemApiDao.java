package pack.model.staticdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.model.RiotApiKeyRotate;

@Repository
public class StaticItemApiDao {
	@Autowired
	RiotApiKeyRotate api;
	
	
}

package pack.model;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.constant.Region;

@Component
public class RiotApiKeyRotate extends RiotApi{
	private String[] key={
			"RGAPI-405a98fb-22c7-4afc-8718-77a577111c33",
			"RGAPI-405a98fb-22c7-4afc-8718-77a577111c33",
			"RGAPI-405a98fb-22c7-4afc-8718-77a577111c33",
			"RGAPI-405a98fb-22c7-4afc-8718-77a577111c33",
			"RGAPI-405a98fb-22c7-4afc-8718-77a577111c33"};
	private int index=0;
	public RiotApiKeyRotate() {
		setKey(key[index]);
		setRegion(Region.KR);
	}
	public void next(){
		index++;
		if(!(index<key.length)){
			index=0;
		}
		setKey(key[index]);
	}
}

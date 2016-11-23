package pack.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pack.model.FeaturedGameManager;

@CrossOrigin(origins="*")
@RestController
public class FeaturedGameController {
	
	@Autowired
	FeaturedGameManager manager;
	
	@RequestMapping(value="/featuredgames",method=RequestMethod.GET)
	@ResponseBody
	public Object getlist1() {
		return manager.getFeaturedGame();
	}
	@RequestMapping(value="/featuredgames/{gameId}",method=RequestMethod.GET)
	@ResponseBody
	public byte[] getDownloadfile(HttpServletResponse response,@RequestParam("encryptionKey") String encryptionKey,@PathVariable("gameId") String gameId)throws IOException{
		//System.out.println("filename:"+filename);
		
		BufferedWriter fw = new BufferedWriter(new FileWriter("c:/my/upload/"+gameId+".bat", false));
		fw.write("cd \"C:\\Riot Games\\League of Legends\\RADS\\solutions\\lol_game_client_sln\\releases\\0.0.0.218\\deploy\\\" "
				+ " \n \"League of Legends.exe\" \"8394\" \"LoLLauncher.exe\" \"\" \"spectator spectator.na.lol.riotgames.com:80 "+encryptionKey+" "+gameId+" KR\"");
		fw.flush();
		fw.close();
		File newFile =new File("c:/my/upload/"+gameId+".bat");
		
		byte[] bytes=FileCopyUtils.copyToByteArray(newFile);
		String fn=new String(newFile.getName().getBytes(),"iso_8859_1");
		response.setHeader("Content-Disposition", "attatchment;filename=\""+fn+"\"");
		response.setContentLength(bytes.length);
		return bytes;
	}
}

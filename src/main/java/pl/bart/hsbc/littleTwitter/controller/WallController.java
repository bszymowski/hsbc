package pl.bart.hsbc.littleTwitter.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.bart.hsbc.littleTwitter.api.WallRS;
import pl.bart.hsbc.littleTwitter.memory.Memory;
import pl.bart.hsbc.littleTwitter.memory.MessageConverter;

@EnableAutoConfiguration
@Controller
@RequestMapping("/wall")
public class WallController {
	
	
	@RequestMapping(value = "/{username}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public WallRS wall(@PathVariable("username") String username) {
		Memory memory = Memory.getInstance();
		return new WallRS(MessageConverter.convertList(memory.getWall(username)));
	}

}

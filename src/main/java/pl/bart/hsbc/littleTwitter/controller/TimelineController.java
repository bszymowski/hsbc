package pl.bart.hsbc.littleTwitter.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.bart.hsbc.littleTwitter.api.TimelineRS;
import pl.bart.hsbc.littleTwitter.memory.Memory;
import pl.bart.hsbc.littleTwitter.memory.MessageConverter;

@EnableAutoConfiguration
@Controller
@RequestMapping("/timeline")
public class TimelineController {

	
	@RequestMapping(value="/{username}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TimelineRS timeline(@PathVariable("username")String username){
		Memory memory = Memory.getInstance();
		return new TimelineRS(MessageConverter.convertList(memory.getTimeLine(username)));
	}
}

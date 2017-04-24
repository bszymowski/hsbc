package pl.bart.hsbc.littleTwitter.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.bart.hsbc.littleTwitter.api.PostingRQ;
import pl.bart.hsbc.littleTwitter.api.PostingRS;
import pl.bart.hsbc.littleTwitter.memory.Memory;

@EnableAutoConfiguration
@Controller
@RequestMapping("/post")
public class PostingController {
	
	@RequestMapping(value= "", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PostingRS postMessage(@RequestBody PostingRQ postingRQ) {
		Memory memory = Memory.getInstance();
		return new PostingRS(memory.addMessage(postingRQ.getUser(), postingRQ.getText()));
	}

}

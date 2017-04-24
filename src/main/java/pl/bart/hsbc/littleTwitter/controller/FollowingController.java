package pl.bart.hsbc.littleTwitter.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.bart.hsbc.littleTwitter.api.FollowingRQ;
import pl.bart.hsbc.littleTwitter.api.FollowingRS;
import pl.bart.hsbc.littleTwitter.api.SimpleFollowingRS;
import pl.bart.hsbc.littleTwitter.entity.UserManager;
import pl.bart.hsbc.littleTwitter.memory.Memory;

@EnableAutoConfiguration
@Controller
@RequestMapping("/follow")
public class FollowingController {
	
	private UserManager userManager = UserManager.getInstance();
	private Memory memory = Memory.getInstance();

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public FollowingRS follow(@RequestBody FollowingRQ followingRQ) {	
		String username = followingRQ.getUser();
		boolean result = memory.follow(username, followingRQ.getFollowing());
		return new FollowingRS(userManager.getUser(username, false).getFollowing(), result);
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public FollowingRS unfollow(@RequestBody FollowingRQ followingRQ) {
		String username = followingRQ.getUser();
		boolean result = memory.unfollow(followingRQ.getUser(), followingRQ.getFollowing());
		return new FollowingRS(userManager.getUser(username, false).getFollowing(), result);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public SimpleFollowingRS checkFollowing(@PathVariable("username") String username) {
		return new SimpleFollowingRS(memory.checkFollow(username));
		
	}
}

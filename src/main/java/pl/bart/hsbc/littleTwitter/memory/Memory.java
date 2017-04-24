package pl.bart.hsbc.littleTwitter.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

import pl.bart.hsbc.littleTwitter.entity.Message;
import pl.bart.hsbc.littleTwitter.entity.MessageFactory;
import pl.bart.hsbc.littleTwitter.entity.User;
import pl.bart.hsbc.littleTwitter.entity.UserManager;

public class Memory {

	private static Memory instance = new Memory();

	private Map<String, BlockingDeque<Message>> messageMap = new ConcurrentHashMap<>();

	private UserManager userManager = UserManager.getInstance();

	private Memory() {

	}

	public static Memory getInstance() {
		return instance;
	}

	public boolean addMessage(String username, String text) {
		Message message = MessageFactory.createMessage(userManager.getUser(username, true), text);
		if (message == null) {
			return false;
		} else {
			if (messageMap.containsKey(username)) {
				BlockingDeque<Message> messageList = messageMap.get(username);
				messageList.addFirst(message);
			} else {
				messageMap.put(username, new LinkedBlockingDeque<>(Arrays.asList(message)));
			}
			return true;
		}
	}

	public List<Message> getWall(String username) {
		BlockingDeque<Message> messageList = messageMap.get(username);
		return (messageList == null) ? Collections.emptyList() : messageList.stream().collect(Collectors.toList());
	}

	public boolean follow(String username, String following) {
		User user = userManager.getUser(username, true);
		return user.follow(following);

	}

	public boolean unfollow(String username, String following) {
		User user = userManager.getUser(username, true);
		return user.unfollow(following);
	}
	
	public Set<String> checkFollow(String username) {
		User user = userManager.getUser(username, false);
		return (user == null) ? Collections.emptySet() : user.getFollowing();
	}

	public List<Message> getTimeLine(String userName) {
		List<Message> result = new ArrayList<>();
		User user = userManager.getUser(userName, false);
		if (user != null) {
			Set<String> follow = user.getFollowing();
			follow.forEach(f -> result.addAll(messageMap.get(f)));
		}
		result.sort((msg1, msg2) -> msg2.getCreationTime().compareTo(msg1.getCreationTime()));
		return result;

	}

}

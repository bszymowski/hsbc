package pl.bart.hsbc.littleTwitter.entity;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

	private static UserManager instance = new UserManager();

	private Map<String, User> users = new HashMap<>();

	private UserManager() {

	}

	public static UserManager getInstance() {
		return instance;
	}

	public User getUser(String name, boolean createIfNoExists) {
		if (users.containsKey(name)) {
			return users.get(name);
		} else {
			if (createIfNoExists) {
				User user = new User(name);
				users.put(name, user);
				return user;
			} else {
				return null;
			}
		}
	}

}

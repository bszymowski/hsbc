package pl.bart.hsbc.littleTwitter.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
	
	private final String name;
	
	private Set<String> following = new HashSet<>();

	public boolean follow(String follow){
		if (following.contains(follow) || name.equals(follow)) {
			return false;
		} else {
			User followingUser = UserManager.getInstance().getUser(follow, false);
			if (followingUser != null) {
				following.add(follow);
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean unfollow(String follow){
		if (following.contains(follow)) {
			User followingUser = UserManager.getInstance().getUser(follow, false);
			if (followingUser != null) {
				following.remove(follow);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	protected User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<String> getFollowing() {
		return following;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	

}

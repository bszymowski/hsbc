package pl.bart.hsbc.littleTwitter.api;

import java.util.Set;

public class SimpleFollowingRS {

	private Set<String> following;
	
	public SimpleFollowingRS(Set<String> following) {
		this.following = following;
	}

	public Set<String> getFollowing() {
		return following;
	}
	
}

package pl.bart.hsbc.littleTwitter.api;

public class FollowingRQ {
	
	private String user;
	
	private String following;

	public String getUser() {
		return user;
	}

	public String getFollowing() {
		return following;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

}

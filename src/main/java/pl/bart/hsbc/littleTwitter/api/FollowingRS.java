package pl.bart.hsbc.littleTwitter.api;

import java.util.Set;

public class FollowingRS extends SimpleFollowingRS{

	private boolean result;

	public FollowingRS(Set<String> following, boolean result) {
		super(following);
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	
	
}

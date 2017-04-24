package pl.bart.hsbc.littleTwitter.api;

public class PostingRQ {

	private String user;
	
	private String text;

	public void setUser(String user) {
		this.user = user;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public String getText() {
		return text;
	}
}

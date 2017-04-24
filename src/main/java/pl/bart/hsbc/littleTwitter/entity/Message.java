package pl.bart.hsbc.littleTwitter.entity;

import java.time.LocalDateTime;

public class Message {
	
	private User user;
	
	private String text;
	
	private LocalDateTime creationTime;

	protected Message(User user, String text) {
		this.user = user;
		this.text = text;
		this.creationTime = LocalDateTime.now();
	}

	public User getUser() {
		return user;
	}
	
	public String getText() {
		return text;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	
	
	
	
	
	
	
	

}

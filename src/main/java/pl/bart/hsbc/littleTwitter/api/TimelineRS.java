package pl.bart.hsbc.littleTwitter.api;

import java.util.List;

public class TimelineRS {

	List<DisplayMessage> messages;

	public TimelineRS(List<DisplayMessage> messages) {
		this.messages = messages;
	}

	public List<DisplayMessage> getMessages() {
		return messages;
	}
}

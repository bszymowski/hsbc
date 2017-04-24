package pl.bart.hsbc.littleTwitter.api;

import java.util.List;

public class WallRS {

	List<DisplayMessage> messages;

	public WallRS(List<DisplayMessage> messages) {
		this.messages = messages;
	}

	public List<DisplayMessage> getMessages() {
		return messages;
	}
	

}

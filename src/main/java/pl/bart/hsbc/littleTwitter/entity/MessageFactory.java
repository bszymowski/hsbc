package pl.bart.hsbc.littleTwitter.entity;

public class MessageFactory {
	
	private static final int TEXT_LIMIT = 140;
	
	public static Message createMessage(User user, String text) {
		return isValidMessage(text) ? new Message(user, text) : null;
	}
	
	private static boolean isValidMessage(String text) {
		return (text != null) && (text.length()) > 0 && (text.length() <= TEXT_LIMIT);
	}

}

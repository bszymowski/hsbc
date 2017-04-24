package pl.bart.hsbc.littleTwitter.memory;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import pl.bart.hsbc.littleTwitter.api.DisplayMessage;
import pl.bart.hsbc.littleTwitter.entity.Message;

public class MessageConverter {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

	public static List<DisplayMessage> convertList(List<Message> messageList) {
		return messageList.stream().map(MessageConverter::convertMessage).collect(Collectors.toList());
	}
	
	private static DisplayMessage convertMessage(Message message) {
		return new DisplayMessage()
				.withAuthor(message.getUser().getName())
				.withText(message.getText())
				.withDateTime(formatter.format(message.getCreationTime()));

	}

}

package pl.bart.hsbc.littleTwitter.api;

public class DisplayMessage {
	
	private String author;
	
	private String text;
	
	private String dateTime;

	public String getAuthor() {
		return author;
	}

	public DisplayMessage withAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getText() {
		return text;
	}

	public DisplayMessage withText(String text) {
		this.text = text;
		return this;
	}

	public String getDateTime() {
		return dateTime;
	}

	public DisplayMessage withDateTime(String dateTime) {
		this.dateTime = dateTime;
		return this;
	}
	

}

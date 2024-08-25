package test.hugh.t.util;

public class TJMessage implements TMessage{
	private String channel;
	private String password;
	public TJMessage(String channel,String password) {
		this.channel = channel;
		this.password = password;
	}
	public String getMsg() {
		return TMessage.JOIN + " " + channel + " " + password;
	}
}

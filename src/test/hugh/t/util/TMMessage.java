package test.hugh.t.util;

public class TMMessage implements TMessage{
	private String channel;
	private String mode;
	public TMMessage(String channel,String mode) {
		this.channel = channel;
		this.mode = mode;
	}
	public String getMsg() {
		return TMessage.MODE + " " + channel + " " + mode;
	}
}

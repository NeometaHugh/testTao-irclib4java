package test.hugh.t.util;

public class TPAMessage implements TMessage{
	private String channel;
	public TPAMessage(String channel) {this.channel = channel;}
	public String getMsg() {
		return TMessage.PART + " " + channel;
	}
}

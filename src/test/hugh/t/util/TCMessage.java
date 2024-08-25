package test.hugh.t.util;

public class TCMessage implements TMessage{
	private String channel;
	private String msg;
	public TCMessage(String channel,String msg) {
		this.channel = channel;
		this.msg = msg;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return TMessage.PRIVMSG + " " + channel + " " + msg; 
	}
}

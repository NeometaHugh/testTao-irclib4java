package test.hugh.t.util;

public class TPRMessage implements TMessage{
	private String to;
	private String msg;
	public TPRMessage(String to,String msg) {
		this.to = to;
		this.msg = msg;
	}
	public String getMsg() {
		return TMessage.PRIVMSG + " " + to + " " + msg;
	}
}

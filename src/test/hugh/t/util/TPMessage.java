package test.hugh.t.util;

public class TPMessage implements TMessage{
	private String password;
	public TPMessage(String password) {
		this.password = password;
	}
	public String getMsg() {
		return TMessage.PASS + " " + password;
	}
}

package test.hugh.t.util;

public class TRMessage implements TMessage{
	private String password,email;
	public TRMessage(String password,String email) {
		this.password = email;
	}
	public String getMsg() {
		return TMessage.PRIVMSG + " NickServ :REGISTER " + password + " " + email;
	}
}

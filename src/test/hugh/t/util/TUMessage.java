package test.hugh.t.util;

public class TUMessage implements TMessage{
	private String nickname;
	private String truename;
	public TUMessage(String nickname,String truename) {
		this.nickname = nickname;
		this.truename = truename;
	}
	public String getMsg() {
		return TMessage.USER + " " + nickname + " 0 * " + truename;
	}
}

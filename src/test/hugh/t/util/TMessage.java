package test.hugh.t.util;

public interface TMessage {
	public static final String PING = "PING";
	public static final String PONG = "PONG";
	public static final String JOIN = "JOIN";
	public static final String NICK = "NICK";
	public static final String USER = "USER";
	public static final String PART = "PART";
	public static final String PASS = "PASS";
	public static final String PRIVMSG = "PRIVMSG";
	public static final String MODE = "MODE";
	public String getMsg();
}

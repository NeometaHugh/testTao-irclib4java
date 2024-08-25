package test.hugh.t;

public class TaoTask implements Runnable{
	private String line;
	public TaoTask(String line) {
		this.line = line;
	}
	public TaoTask() {}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public void run() {}
}

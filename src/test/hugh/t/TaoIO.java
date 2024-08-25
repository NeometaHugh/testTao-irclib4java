package test.hugh.t;

import java.io.*;

import test.hugh.t.util.TMessage;

public class TaoIO {
	private BufferedReader r;
	private PrintWriter w;
	private TaoTask task;
	private volatile boolean connected = false;
	private TThreadPool<TaoTask> pool;
	private Thread deamon;
	public TaoIO(InputStreamReader r,OutputStreamWriter w,TThreadPool<TaoTask> pool,TaoTask task) {
		this.r = new BufferedReader(r);
		this.w = new PrintWriter(w,true);
		this.pool = pool;
		this.task = task;
		deamon = new Thread(() -> {
			try {
                String line;
                while ((line = this.r.readLine()) != null) {
                    if (line.startsWith("PING")) {
                        this.w.println("PONG " + line.split(" ")[1]);
                        Thread.sleep(50);
                    }else{
                    	task.setLine(line);
                    	pool.execute(task);
                    }
                }
            } catch (Exception e) {}
		});
		deamon.start();
	}
	public BufferedReader getInput() {
		synchronized (r) {
		return r;
		}
	}
	public TThreadPool<TaoTask> getPool() {
		synchronized (pool) {
			return pool;
		}
	}
	public PrintWriter getOutput() {
		synchronized (w) {
		return w;
		}
	}
	public TaoTask getTask() {
		synchronized (this.task) {
		return task;
		}
	}
	public void setTask(TaoTask task) {
		synchronized (this.task) {
		this.task = task;
		}
	}
	public boolean isConnected() {
		return connected;
	}
	public void send(TMessage msg) {
		synchronized (w) {
		w.println(msg.getMsg());
		}
	}
}

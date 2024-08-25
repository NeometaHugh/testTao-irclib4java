package test.hugh.t;

import java.util.*;
import java.util.concurrent.atomic.*;

public class DTThreadPool<Task extends Runnable> implements TThreadPool<Task>{
	private static final int DEFAULT = 10;
	private static final int MIN = 5;
	private final LinkedList<Task> tasks = new LinkedList<Task>();
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
	private int workerNum = DEFAULT;
	private AtomicLong threadNum = new AtomicLong();
	
	public DTThreadPool() {init(DEFAULT);}
	public DTThreadPool(int num) {
		workerNum = num > DEFAULT ? num : (num < MIN ? MIN : DEFAULT);
		init(workerNum);
	}
	private void init(int num) {
		for (int i = 0;i < num;i++) {
			Worker worker  = new Worker();
			workers.add(worker);
			Thread thread = new Thread(worker,"tp"+threadNum.incrementAndGet());
			thread.start();
		}
	}
	public void execute(Task task) {
		if (task != null) {
			synchronized (tasks) {
				tasks.addLast(task);
				tasks.notify();
			}
		}
	}
	
	public void shutdown() {
		for (Worker worker : workers)worker.shutdown();
	}
	
	public void addWorkers(int num) {
		synchronized (tasks) {
			init(num + this.workerNum);
			this.workerNum += num;
		}
	}
	public void removeWorker(int num) {
		synchronized(tasks) {
			if (num >= this.workerNum) {
				throw new IllegalArgumentException("out of bonous");
			}
			int count = 0;
			while (count < num) {
				Worker worker = workers.get(count);
				if (workers.remove(worker)) {
					worker.shutdown();
					count++;
				}
			}
			this.workerNum -= count;
		}
	}
	public int getTaskSize() {
		return tasks.size();
	}
	private class Worker implements Runnable {
		private volatile boolean running = true;
		public void run() {
			while (running) {
				Task task = null;
				synchronized(tasks) {
					while (tasks.isEmpty()) {
						try {
							tasks.wait();
						}catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							return;
						}
					}
					task = tasks.removeFirst();
				}
				if (task != null) {
					try {
						task.run();
					}catch (Exception e) {}
				}
			}
		}
		public void shutdown() {
			running = false;
		}
	}
}

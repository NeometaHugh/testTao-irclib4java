package test.hugh.t;

public interface TThreadPool<Task extends Runnable> {
	public void execute(Task task);
	public void shutdown();
	public void addWorkers(int num);
	public void removeWorker(int num);
	public int getTaskSize();
}

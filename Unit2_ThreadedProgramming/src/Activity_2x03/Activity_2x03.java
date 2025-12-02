package Activity_2x03;

class Data {
	private String item;
	private boolean available = false;

	/* Synchronized method get
	 * to obtain the number of
	 * the shared data. */
	public synchronized String get() {
		while (!available) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.err.println(e.toString());
			}
		}
		available = false;
		notifyAll();
		return item;
	}

	/* Synchronized method set
	 * to update the number of
	 * the shared data. */
	public synchronized void set (String message) {
		while (available) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.err.println(e.toString());
			}
		}
		item = message;
		available = true;
		notifyAll();
	}
}

class Producer extends Thread {
	private Data data;

	public Producer (Data d) {
		this.data = d;
	}

	public void run() {
		for (int i=1; i<=25;i++) {
			data.set("PING...");
			try {
				sleep(500);
			}
			catch (InterruptedException e) {
				System.err.println(e.toString());
			}
			data.set("...PONG");
			try {
				sleep(500);
			}
			catch (InterruptedException e) {
				System.err.println(e.toString());
			}
		}
	}
}

class Consumer extends Thread {
	private Data data;

	public Consumer (Data d) {
		this.data = d;
	}

	public void run() {
		String value = "";
		for (int i=1; i<=50;i++) {
			value = data.get();
			System.out.println(value);
		}
	}
}


public class Activity_2x03 {

	public static void main(String[] args) {
		Data data = new Data();
		Producer p = new Producer(data);
		Consumer c = new Consumer(data);
		p.start();
		c.start();
	}
}
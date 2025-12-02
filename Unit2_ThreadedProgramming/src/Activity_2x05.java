import java.util.concurrent.Semaphore;

class Greeting extends Thread {
	private Semaphore sem;
	private int id;	
	
	Greeting (int id, Semaphore s) {
		this.id = id;
		this.sem = s;
	}	
	public void run() {
		if (id == 1)
		{
			try {
				sem.acquire();
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
		System.out.println("Hello, I am thread " + id);
		if (id == 2) {
			sem.release();
		}
	}
}

public class Activity_2x05 {	
		public static void main(String[] args) {			
			Semaphore s = new Semaphore(0);
			Greeting t1 = new Greeting(1,s);
			Greeting t2 = new Greeting(2,s);
			t1.start();
			t2.start();		
		}	
}


import java.util.Random;

class Baton {
	private int next;
	
	Baton () {
		this.next = 0;
	}
	synchronized public void batonPassing(int id){
		this.next = id;
		notifyAll();
	}
	synchronized public void batonReceiving(int id) throws InterruptedException{
		while(next != id){     
			wait();
		}
	}
}

class Runner extends Thread { 
	private int id;
	private Baton b;
	private int num_runners;
	
	Runner (int id, Baton b, int num_runners) {
		this.id = id;
		this.b = b;
		this.num_runners=num_runners;
	}
	
	public void run() {
		try{
			b.batonReceiving(id);   
			System.out.println("Runner " + id + " starts...");
			Thread.sleep(new Random().nextInt(1000)*10);
			if(id != num_runners)
			{
				int receiver = id+1;     
				System.out.println("Pass the baton to runner " + receiver);
				b.batonPassing(receiver);
			} else{
				System.out.println("The race ends!");
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class Activity_2x09 {
	public static void main(String[] args) {
		final int num_runners = 5;
		Baton b = new Baton();
		Runner run[] = new Runner[num_runners];
		for(int i= 0; i < run.length; i++){
			run[i]= new Runner(i+1,b,num_runners);
			run[i].start();
		}
		System.out.println("All threads created.");
		b.batonPassing(1);
		System.out.println("The race begins!");
		try{
			for(int i= 0; i < run.length; i++){
				run[i].join();
			}
		} catch(InterruptedException ex) {
			System.out.println("Main thread interrupted."); 
		}
		System.out.println("All threads are finished.");
	}
}

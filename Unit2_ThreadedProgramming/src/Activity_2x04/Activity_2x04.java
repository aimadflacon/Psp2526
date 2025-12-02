package Activity_2x04;

class Data { 
	
	private String message; 
	private boolean turnConsumer = false;
	private int turnProducer = 1;
	
	public synchronized String get() { 
		
		while(turnConsumer == false){  
			try {
				wait();
			}
			catch (InterruptedException e){}
		}
		
		turnConsumer=false; //producer turn now
		
		notifyAll();
		return message; //returns the shared resource
	}	
	
	public synchronized void put(String value, int numProd) { 
		
		while (turnConsumer == true || turnProducer != numProd){
			try {
				wait();
			}
			catch (InterruptedException e){}
		}
		message = value; //sets the String in the shared resource
		turnConsumer = true; //turn of the consumer
		turnProducer++;
		if(turnProducer == 8)
			turnProducer = 1;
		notifyAll();
	}
}


class Producer extends Thread { 

	private Data monitor; 
	private int numProducer;
	private String s;

	public Producer(Data m, int np, String s) { 
		this.numProducer = np;
		this.monitor = m;
		this.s = s;
	} 

	public void run() { 
		for (int i = 1; i <= 5; i++) { //each producer sets its day 5 times
			monitor.put(s, numProducer); 
			try { 
				sleep(100); 
			} catch (InterruptedException e) { }

		}
	}
}

class Consumer extends Thread { 
	
	private Data monitor;
	
	public Consumer (Data m) { 
		
		monitor = m;
	} 
	
	public void run() { 
		
		String value;
		
		for (int i = 1; i <= 35; i++) {
/* each producer sets its day 5 times,
 * so 7x5=35 in total */
			value = monitor.get();
			System.out.println("i = " + i + " --> " + value);   
		}
	}
}

public class Activity_2x04 {
	public static void main(String[] args) { 
		Data d = new Data(); 
	
		Producer mon = new Producer(d, 1, "Monday");
		Producer tue = new Producer(d, 2, " Tuesday");
		Producer wed = new Producer(d, 3, "  Wednesday");
		Producer thu = new Producer(d, 4, "   Thursday");
		Producer fri = new Producer(d, 5, "    Friday");
		Producer sat = new Producer(d, 6, "     Saturday");
		Producer sun = new Producer(d, 7, "      Sunday");
		
		Consumer c = new Consumer(d);
		
		mon.start();
		tue.start();
		wed.start();
		thu.start();
		fri.start();
		sat.start();
		sun.start();
		
		c.start();		
			
	}
	
}

class Writer extends Thread {
	private int[] array;
	private int c = 1;

	Writer (int[] v) { 
		this.array = v; 
	}	

	private void write() {
		for (int i=0; i<array.length; i++) {
			array[i] = c;
		}
		c++;
	}

	public void run() {
		while(true){
			synchronized(this.array) {
				write();
			}
		}
	}
}

class Reader extends Thread {
	private int[] array;

	Reader (int[] v) {
		this.array = v;
	}
	
	private void check(){
		int diff = 0;
		System.out.print(array[0] + " "); // first element or array
		for (int i=1; i<array.length; i++) { // all elements of array but first one
			if(array[0] != array[i]) {
				System.out.println("\tReader thread error: DIFFERENT elements.");
				diff = 1;
				break;
			}
			System.out.print(array[i] + " ");
		}
		if(diff == 0) { // if all ok
			System.out.println("Reader thread: SAME elements.");
		}
	}
	public void run() {
		while(true){
			synchronized(this.array) {
				check();
			}
		}
	}
}

public class Activity_2x06 {
	public static void main(String[] args) {
		int[] array = new int[3];	// solution with only 3 items in the array, not 100.	
		Reader r = new Reader(array); 
		Writer w = new Writer(array); 
		r.start();
		w.start();
	}
} 

import java.util.Random;

class Barrier {
	private int places[];
	private int free_places;

	Barrier (int numPlaces) { 
		places = new int[numPlaces]; 
		for(int i=0; i<places.length; i++) {
			places[i] = 0;
		}
		free_places = places.length;
	}

	synchronized public int enter(int num_car) throws InterruptedException {
		int num_place=0;
		//		status();
		while(free_places == 0) {      
			System.out.println("...Car " + num_car + " waiting...");
			wait();
		}
		while(places[num_place] != 0) {
			num_place++;
		}
		places[num_place] = num_car; 
		free_places--;
		return num_place;
	}

	synchronized public void leave(int num_place) {
		places[num_place] = 0; 
		free_places++;
		notifyAll();
	}

	synchronized public void status(){
		System.out.println("Parking status: "); 
		for(int i=0; i<places.length; i++) {     
			System.out.print("[" + places[i] + "] ");
		}
		System.out.println("\n"); 
	}

}

class Car extends Thread { 
	private int id;
	private Barrier bar;

	Car (int id, Barrier bar) {
		this.id = id;
		this.bar = bar;
	}

	public void run() {
		try{
			//Entering parking
			Thread.sleep(new Random().nextInt(1000)*10);
			System.out.println("Car " + id + " entering parking.");
			//Parking
			int num_place = bar.enter(id);    
			System.out.println("Car " + id + " parked in place " + num_place + ".");
			bar.status();
			Thread.sleep(new Random().nextInt(1000)*10);
			//Leaving
			bar.leave(num_place);    
			System.out.println("Car " + id + " leaving parking.");
			bar.status();
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class Activity_2x08 {
	public static void main(String[] args) {
		final int NUM_PLACES = 5;
		final int NUM_CARS = 10;

		//Launching parking with cars and barrier
		Barrier bar = new Barrier(NUM_PLACES);
		Car cars[] = new Car[NUM_CARS];
		for(int i= 0; i < cars.length; i++){
			cars[i]= new Car(i+1, bar);
			cars[i].start();
		}
		try {
			for(int i= 0; i < cars.length; i++) {
				cars[i].join();
			}
		} catch(InterruptedException ex) {
			System.out.println("Main thread interrupted.");
		}
	}
}

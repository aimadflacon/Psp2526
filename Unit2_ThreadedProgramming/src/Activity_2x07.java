
import java.util.Random;

class Results {
	public static int profits;
	public static int customers_attended;
}

class Checkout { 
	class Node {
		int customer_id;
		Node next;
	}
	Node root, back;

	public Checkout() {
		root = null;
		back = null;
	}
	private boolean empty () {
		if(root == null)
			return true;
		else
			return false;
	}

	synchronized public void wait (int id_cus) throws InterruptedException { 
		Node new_n;
		new_n = new Node ();
		new_n.customer_id = id_cus;
		new_n.next = null;
		if(empty ()) {
			root = new_n;
			back = new_n;
		} else{
			back.next = new_n;
			back = new_n;
		}
		while(root.customer_id != id_cus){
			// Customer locked till his/her turn
			wait();
		}
	}

	synchronized public void attend (int pay) throws InterruptedException {
		if(root == back) {
			root = null;
			back = null;
		} else {
			root = root.next;
		}
		int time_attend = new Random().nextInt(1000);
		Thread.sleep(time_attend);
		Results.profits += pay;
		Results.customers_attended++;
		notify();
	}

	synchronized public void print() {
		Node qeue=root;
		while(qeue!=null) {
			System.out.print(qeue.customer_id + ", ");
			qeue=qeue.next;
		}
		System.out.println();
	}
}

	
class Customer extends Thread { 
	private int id;
	private Checkout c;
	Customer (int id, Checkout c) {
		this.id = id;
		this.c = c;
	}
	public void run() {
		try{     
			System.out.println("Customer " + id + " shopping");
			Thread.sleep(new Random().nextInt(2000)); 
			c.wait(id);     
			System.out.print("Customer " + id + " paying. Qeue list: ");
			c.print();
			c.attend(new Random().nextInt(200));     
			System.out.println("Customer " + id +  " attended");
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}	
}

public class Activity_2x07 {
	
	private static int N_CUSTOMERS = 30; // 30 customers
	private static int N_CHECKOUTS = 3; // 3 checkouts
	
	public static void main(String[] args) throws InterruptedException {
		
		Checkout c[] = new Checkout[N_CHECKOUTS];
		for(int i= 0; i < c.length; i++){
			c[i]= new Checkout();
		}
		Customer cus[] = new Customer[N_CUSTOMERS];
		for(int i= 0; i < cus.length; i++){ 
			int nCheckout = new Random().nextInt(N_CHECKOUTS); //Randomly assigned to a checkout
			cus[i]= new Customer(i,c[nCheckout]);
			cus[i].start();
		}
		try{
			for(int i= 0; i < cus.length; i++){
				cus[i].join();
			}
		} catch(InterruptedException ex) {
			System.out.println("Main Thread Interrupted.");
		}
		
		System.out.println("---Supermarket closed---");    
		System.out.println("Profits: " + Results.profits);     
	}
}
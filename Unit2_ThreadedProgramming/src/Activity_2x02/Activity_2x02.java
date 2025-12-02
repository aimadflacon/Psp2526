package Activity_2x02;


class Tic extends Thread {
	int time;
	int N;
	public Tic (int time, int N) {
		this.time=time;
		this.N=N;
	}
	@Override
	public void run () {
		for (int i=0;i<N;i++) {
			try {
				System.out.println(" TIC...");
				sleep(time);
			}
			catch (InterruptedException e) {
				System.out.println("TIC interrupted!");
			}
		}
	}
}

class Tac extends Thread {
	int time;
	int N;
	public Tac (int time, int N) {
		this.time=time;
		this.N=N;
	}
	@Override
	public void run () {
		for (int i=0;i<N;i++) {
			try {
				System.out.print(" ...TAC");
				sleep(time);
			}
			catch (InterruptedException e) {
				System.out.println("TAC interrupted!");
			}
		}
	}
}

public class Activity_2x02 {
	public static void main(String[] args) {
		int time = 500;
		int N = 10;
		Tic t1 = new Tic(time,N);
		Tac t2 = new Tac(time,N);
		t1.start();
		t2.start();
		System.out.println("*** TIC-TAC started ***\n");
	}
}

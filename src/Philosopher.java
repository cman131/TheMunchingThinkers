
public class Philosopher extends Thread {
	private final int id;
	private IFork left;
	private IFork right;
	private final boolean rHanded;
	private final int nTimes;
	private final long thinkMillis;
	private final long eatMillis;
	
	public Philosopher(int id, IFork left, IFork right, boolean rHanded,
                   int nTimes, long thinkMillis, long eatMillis){
		this.id = id;
		this.left = left;
		this.right = right;
		this.rHanded = rHanded;
		this.nTimes = nTimes;
		this.thinkMillis = thinkMillis;
		this.eatMillis = eatMillis;
	}
	
	@Override
	public void run(){
		for(int i=this.nTimes+(this.nTimes==0 ? 0 : 1); i!=1; i--){
			long t = (long)(Math.random()*thinkMillis);
			System.out.println("Philosopher "+id+" thinks for "+t+" time units.");
			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {}
			IFork myFork = (rHanded ? right : left);
			String forkName = (rHanded ? "right" : "left");
			System.out.println("Philosopher "+id+" goes for "+forkName+" fork.");
			myFork.acquire();
			System.out.println("Philosopher "+id+" has "+forkName+" fork.");
			Thread.yield();
			
			IFork myFork2 = (rHanded ? left : right);
			String fork2Name = (rHanded ? "left" : "right");
			System.out.println("Philosopher "+id+" goes for "+fork2Name+" fork.");
			myFork2.acquire();
			System.out.println("Philosopher "+id+"has "+fork2Name+" fork.");
			Thread.yield();
			
			t = (long)(Math.random()*eatMillis);
			System.out.println("Philosopher "+id+" eats for "+t+" time units");
			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {}
			
			right.release();
			System.out.println("Philosopher "+id+" releases right fork.");
			left.release();
			System.out.println("Philosopher "+id+" releases left fork.");
		}
	}
}

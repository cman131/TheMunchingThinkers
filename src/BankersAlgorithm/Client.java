package BankersAlgorithm;

public class Client extends Thread{
	
	private Banker banker;
	private int nUnits;
	private int nRequests;
	private long minSleepMillis;
	private long maxSleepMillis;
	
	public Client(String name, Banker banker, int nUnits, int nRequests, long minSleepMillis, long maxSleepMillis){
		super(name);
		this.banker = banker;
		this.nUnits = nUnits;
		this.nRequests = nRequests;
		this.minSleepMillis = minSleepMillis;
		this.maxSleepMillis = maxSleepMillis;
	}
	
	public void run(){
		int randomVal = (int) (Math.floor(Math.random() * nUnits) + 1);
		for(int i = 0; i < nRequests; i++){
			if (banker.remaining() == 0){
					banker.release(nUnits);
			}
			else{
				banker.request(randomVal);
			}
			long sleepDuration = (long) Math.random() * (maxSleepMillis - minSleepMillis);
			try {
				Thread.sleep(sleepDuration);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		banker.release();
	}
}

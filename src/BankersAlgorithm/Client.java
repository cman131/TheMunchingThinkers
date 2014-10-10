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
		int randomVal = (int) (Math.floor(Math.random() * (nUnits-1)) + 1);
		banker.setClaim(randomVal);
		//int totalRequestedResources = 0;
		
		for(int i = 0; i < nRequests; i++){
			//System.out.println(this.getName() + "is on its "+ i);
			if (banker.remaining() == 0){
				if (banker.allocated() != 0){
					banker.release();
				}
			}
			else{
				int randomVal2 = (int) (Math.floor(Math.random() * (randomVal-banker.allocated())) + 1);
				banker.request(randomVal2);
			//	totalRequestedResources += randomVal2;
			}
			long sleepDuration = (long) (Math.random() * (maxSleepMillis - minSleepMillis));
			try {
				Thread.sleep(sleepDuration);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (banker.allocated()!=0){
			banker.release();
		}
	}
}

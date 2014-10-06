
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean l = false;
		int np = 4;
		int nt = 10;
		long tm = 0;
		long em = 0;
		
		ArrayList<IFork> forks = new ArrayList<IFork>();
		ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
		for(int i=0; i<np; i++){
			forks.add(new Fork());
		}
		for(int i=0; i<np; i++){
			philosophers.add(new Philosopher(i,forks.get(i),forks.get((np+i-1)%np),(l ? (i%2==0 ? true : false) : true),nt,tm,em));
			philosopher.get(i).start();
		}
	}

}

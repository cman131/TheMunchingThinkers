import java.util.HashMap;

public class Banker {
	private int nUnits;
	private HashMap<String,Integer> claims = new HashMap<String,Integer>();
	private HashMap<String,Integer> allocated = new HashMap<String,Integer>();
	
	public Banker(int nUnits){
		this.nUnits = nUnits;
	}
	
	public void setClaim(int nUnits){
		Thread cur = Thread.currentThread();
		if(claims.get(cur.getName())!=null || nUnits<=0 || nUnits>this.nUnits){
			System.exit(1);
		}
		claims.put(cur.getName(),nUnits);
		allocated.put(cur.getName(),0);
		System.out.println("Thread "+cur.getName()+" sets a claim for "+nUnits+" units.");
	}
	
	public boolean request(int nUnits){
		Thread cur = Thread.currentThread();
		String name = cur.getName();
		if(claims.get(name)==null || nUnits<=0 || nUnits>claims.get(name)){
			System.exit(1);
		}
		System.out.println("Thread "+name+" requests "+nUnits+" units.");
		
		boolean safe = true;
		if(safe){
			System.out.println("Thread "+name+" has "+nUnits+" units allocated.");
			allocated.put(name, nUnits+allocated.get(name));
			claims.put(name, claims.get(name)-nUnits);
		}
		else{
			System.out.println("Thread "+name+" waits.");
			//wait for change in state
		}
	}
	
	public void release(int nUnits){
		
	}
	
	public int allocated(){
		
	}
	
	public int remaining(){
		
	}
}

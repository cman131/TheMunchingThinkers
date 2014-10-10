package BankersAlgorithm;

import java.util.HashMap;

public class Banker {
	private int nUnits;
	private HashMap<String,Integer> claims = new HashMap<String,Integer>();
	private HashMap<String,Integer> allocated = new HashMap<String,Integer>();
	
	public Banker(int nUnits){
		this.nUnits = nUnits;
	}
	
	public synchronized void setClaim(int nUnits){
		Thread cur = Thread.currentThread();
		if(claims.get(cur.getName())!=null || nUnits<=0 || nUnits>this.nUnits){
			System.exit(1);
		}
		claims.put(cur.getName(),nUnits);
		allocated.put(cur.getName(),0);
		System.out.println("Thread "+cur.getName()+" sets a claim for "+nUnits+" units.");
	}
	
	public synchronized boolean request(int nUnits){
		Thread cur = Thread.currentThread();
		String name = cur.getName();
		if(claims.get(name)==null || nUnits<=0 || nUnits>claims.get(name)){
			System.exit(1);
		}
		System.out.println("Thread "+name+" requests "+nUnits+" units.");
		
		while(this.nUnits<nUnits){
			System.out.println("Thread "+name+" waits.");
            try {
                cur.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		System.out.println("Thread "+name+" has "+nUnits+" units allocated.");
		allocated.put(name, nUnits+allocated.get(name));
		claims.put(name, claims.get(name)-nUnits);
		this.nUnits-=nUnits;

        //TODO Fix this! This return value was written by someone who has no bleeding idea of what's going on.
        return true;
	}
	
	public synchronized void release(){
		release(allocated.get(Thread.currentThread().getName()));
	}

	public synchronized void release(int nUnits){
		Thread cur = Thread.currentThread();
		String name = cur.getName();

		if(claims.get(name)==null || nUnits<=0 || nUnits>allocated.get(name)){
			System.exit(1);
		}
		System.out.println("Thread "+name+" releases "+nUnits+" units.");
		allocated.put(name,allocated.get(name)-nUnits);
		this.nUnits+=nUnits;
		notifyAll();
	}
	
	public synchronized int allocated(){
		return allocated.get(Thread.currentThread().getName());
	}
	
	public synchronized int remaining(){
		if (claims.get(Thread.currentThread().getName()) != null){
			return claims.get(Thread.currentThread().getName());
		}
		else return -1;
	}
}

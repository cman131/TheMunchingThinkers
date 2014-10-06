import java.util.ArrayList;

import com.sun.security.ntlm.Client;

private final static int bankerUnits = 5;
private final static int clientCount = 2;
private final static int clientUnits = 2;
private final static int clientRequests = 4;
private final static long minSleepMillis = 100;
private final static long maxSleepMillis = 1000;

public class Driver {
	public static void main(String[] args){		Banker banker = new Banker(bankerUnits);
		ArrayList<Client> clients = new ArrayList<Client>();
		for(int i=0; i<clientCount; i++){
			clients.add(new Client("Client "+i, banker, clientUnits, clientRequests, minSleepMillis, maxSleepMillis));
			clients.get(i).start();
		}
	}
}

import java.util.ArrayList;

public class Driver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        boolean l = false; //left right disabled by default.  If enabled, even => right handed | odd => left handed
        int numPhilosophers = 4; // also the number of forks
        int numCycles = 10; //num think/eat cycles
        long thinkTime = 0; // milliseconds
        long eatTime = 0; // milliseconds

        if (args.length == 5) {
            if (args[0] == "-l") {
                l = true;
            }
            numPhilosophers = Integer.valueOf(args[1]);
            numCycles = Integer.valueOf(args[2]);
            thinkTime = Integer.valueOf(args[3]);
            eatTime = Integer.valueOf(args[4]);
        } else if (args.length == 4) {
            numPhilosophers = Integer.valueOf(args[0]);
            numCycles = Integer.valueOf(args[1]);
            thinkTime = Integer.valueOf(args[2]);
            eatTime = Integer.valueOf(args[3]);
        } else {
            System.out.println("Invalid command line args. Expected numPhilosophers, numCycles, thinkTime, eatTime.  Resorting to default values.");
        }
        ArrayList<IFork> forks = new ArrayList<IFork>();
        ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
        for (int i = 0; i < numPhilosophers; i++) {
            forks.add(new Fork());
        }
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers.add(new Philosopher(i, forks.get(i), forks.get((numPhilosophers + i - 1) % numPhilosophers), (l ? (i % 2 == 0 ? true : false) : true), numCycles, thinkTime, eatTime));
            philosophers.get(i).start();
        }
    }

}

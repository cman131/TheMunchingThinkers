import java.util.ArrayList;

public class Driver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        boolean l = false; //left right disabled by default.  If enabled, even => right handed | odd => left handed
        int np = 4; //numPhilos; numForks
        int nt = 10; //num think/eat cycles
        long tm = 0; //think time, millis
        long em = 0; //eat time, millis

        if (args.length == 5) {
            if (args[0] == "-l") {
                l = true;
            }
            np = Integer.valueOf(args[1]);
            nt = Integer.valueOf(args[2]);
            tm = Integer.valueOf(args[3]);
            em = Integer.valueOf(args[4]);
        } else if (args.length == 4) {
            np = Integer.valueOf(args[0]);
            nt = Integer.valueOf(args[1]);
            tm = Integer.valueOf(args[2]);
            em = Integer.valueOf(args[3]);
        } else {
            System.out.println("Invalid command line args.  Resorting to default values.");
        }
        ArrayList<IFork> forks = new ArrayList<IFork>();
        ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
        for (int i = 0; i < np; i++) {
            forks.add(new Fork());
        }
        for (int i = 0; i < np; i++) {
            philosophers.add(new Philosopher(i, forks.get(i), forks.get((np + i - 1) % np), (l ? (i % 2 == 0 ? true : false) : true), nt, tm, em));
            philosophers.get(i).start();
        }
    }

}

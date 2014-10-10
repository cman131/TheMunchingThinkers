package DiningPhilosophers;
public class Fork implements IFork {

    private volatile boolean held;

    @Override
    public void acquire() {
        while (held) {
            Thread.yield();
        }
        synchronized (this) {
            held = true;
        }
    }

    @Override
    public void release() {
        if (!held) {
            throw new IllegalStateException("Cannot release an unheld fork.");
        }
        synchronized (this) {
            held = false;
        }
    }

}

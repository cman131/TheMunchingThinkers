import java.lang.Exception;

public class Fork implements IFork {

    private volatile Thread holding;
	
	@Override
	public void acquire() {
		while (holding != null) {
            Thread.yield();
        }
        holding = Thread.currentThread();
	}

	@Override
	public void release() {
        if (!holding.equals(Thread.currentThread())) {
            throw new IllegalStateException("Cannot release a fork that the calling thread does not hold.");
        }
        holding = null;
	}

}

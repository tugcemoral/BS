package tr.edu.ege.computergraphics.throwers;

import java.util.List;
import java.util.Vector;

import tr.edu.ege.computergraphics.bubbles.Bubble;
import tr.edu.ege.computergraphics.gui.BubblesGame;

/**
 * Balon atan sinif.
 * 
 */
public abstract class Thrower implements Runnable {

	private static List<Bubble> bubbleList = new Vector<Bubble>();

	private static final double DIVISION_CONSTANT = 1.2;

	public static List<Bubble> getBubbleList() {
		return bubbleList;
	}

	protected Thread thread;

	public void run() {
		while (thread != null) {
			try {
				Thread.sleep(getDelay());
			} catch (InterruptedException e) {
				break;
			}
			if (thread != null)
				sendBubble();
		}
	}

	public abstract void sendBubble();

	public synchronized void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.setPriority(Thread.MIN_PRIORITY);
			thread.start();
		}
	}

	public void stop() {
		getBubbleList().clear();
		thread = null;
	}

	protected long findDelay() {
		long delay = (long) (getTimeConstant() + Math.random()
				* getTimeConstant());
		return delay;
	}

	protected double findDivision() {
		int level = BubblesGame.getInstance().getLevel();
		return Math.pow(DIVISION_CONSTANT, level);
	}

	protected long getDelay() {
		return (long) (findDelay() / findDivision());
	}

	protected abstract long getTimeConstant();
}

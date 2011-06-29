package tr.edu.ege.computergraphics.throwers;

import tr.edu.ege.computergraphics.bubbles.NormalBubble;

/**
 * Belirli araliklarla normal balon atan sinif.
 * 
 */
public class NormalBubbleThrower extends Thrower {

	public void run() {
		while (thread != null) {
			// long delay = (long) (DELAY + Math.random() * DELAY);
			// accelerate(delay);
			sendBubble();
			try {
				Thread.sleep(getDelay());
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public void sendBubble() {
		super.getBubbleList().add(new NormalBubble());
	}

	@Override
	protected long getTimeConstant() {
		return 800;
	}
}

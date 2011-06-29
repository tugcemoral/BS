package tr.edu.ege.computergraphics.throwers;

import tr.edu.ege.computergraphics.bubbles.DeathBubble;

/**
 * Belirli araliklarla �l�m balonu atan sinif.
 * 
 */
public class DeathBubbleThrower extends Thrower {

	@Override
	protected long getTimeConstant() {
		return 20000;
	}

	public void sendBubble() {
		super.getBubbleList().add(new DeathBubble());
	}

}

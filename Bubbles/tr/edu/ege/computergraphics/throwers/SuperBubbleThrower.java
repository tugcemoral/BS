package tr.edu.ege.computergraphics.throwers;

import tr.edu.ege.computergraphics.bubbles.SuperBubble;

public class SuperBubbleThrower extends Thrower {

	@Override
	public void sendBubble() {
		getBubbleList().add(new SuperBubble());
	}

	@Override
	protected long getTimeConstant() {
		return 25000;
	}

	@Override
	protected long getDelay() {
		return findDelay();
	}
}

package tr.edu.ege.computergraphics.throwers;

import tr.edu.ege.computergraphics.bubbles.LifeBubble;

public class LifeBubbleThrower extends Thrower {

	public void sendBubble() {
		super.getBubbleList().add(new LifeBubble());
	}

	@Override
	protected long getTimeConstant() {
		return 40000;
	}
}

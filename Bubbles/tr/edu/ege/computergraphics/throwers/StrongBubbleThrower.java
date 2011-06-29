package tr.edu.ege.computergraphics.throwers;

import tr.edu.ege.computergraphics.bubbles.StrongBubble;

public class StrongBubbleThrower extends Thrower {

	@Override
	public void sendBubble() {
		getBubbleList().add(new StrongBubble());
	}

	@Override
	protected long getTimeConstant() {
		return 5000;
	}

}

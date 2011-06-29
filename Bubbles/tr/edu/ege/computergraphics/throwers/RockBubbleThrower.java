package tr.edu.ege.computergraphics.throwers;

import java.util.List;
import java.util.Vector;

import tr.edu.ege.computergraphics.bubbles.RockBubble;

public class RockBubbleThrower extends Thrower {

	private static List<RockBubble> rockBubbles = new Vector<RockBubble>();

	public static List<RockBubble> getRockBubbleList() {
		return rockBubbles;
	}

	@Override
	public void sendBubble() {
		getBubbleList().add(new RockBubble());
	}

	@Override
	public void stop() {
		getRockBubbleList().clear();
		thread = null;
	}

	@Override
	protected long getTimeConstant() {
		return 22500;
	}
}

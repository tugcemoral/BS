package tr.edu.ege.sst.samplechat.listener;

/**
 * This interface indicates that implementers want to receive a message string
 * from UI.
 * 
 */
public interface IUIMessageGettable {

	/**
	 * This message is implemented to receive a message content from UI.
	 * 
	 * @param messageContent
	 *            incoming message content.
	 */
	public void messageReceivedFromUI(String messageContent);

}

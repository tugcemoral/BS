package tr.edu.ege.sst.infrastructure;

/**
 * This interface lets implementers to receive messages from other clients.
 */
public interface IMessageReceivable {

	/**
	 * This method is used from message receiver thread to notify clients that
	 * new message is received asynchronously.
	 * 
	 * @param message
	 *            incoming {@link Message} instance.
	 */
	public void messageReceived(Message message);

}

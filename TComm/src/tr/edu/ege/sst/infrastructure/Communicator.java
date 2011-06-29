package tr.edu.ege.sst.infrastructure;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * The communicator lets its users to communicate (send and receive messages)
 * with other clients.
 */
public class Communicator {

	/**
	 * The message receiver instance which allows to receive message from
	 * another thread.
	 */
	private MessageReceiver receiver;

	/**
	 * Creates and initializes a new instance of {@link Communicator}. Creates a
	 * {@link MessageReceiver} thread to receive messages and pass received to
	 * users. Also starts this thread.
	 * 
	 * @param receiverPortNumber
	 *            receiver port number to create a {@link ServerSocket}. This
	 *            socket is able to get external messages.
	 * @throws IOException
	 *             when a problem occurred while creating {@link ServerSocket}
	 */
	public Communicator(int receiverPortNumber) throws IOException {
		// initialize a socket to receive data.
		ServerSocket serverSocket = new ServerSocket(receiverPortNumber);
		// create receiver thread and start it.
		receiver = new MessageReceiver(serverSocket);
		receiver.start();
	}

	/**
	 * This method must be called from users of this class in order to get
	 * received messages. Users add themselves as listeners.
	 * 
	 * @param messageReceivable
	 *            {@link IMessageReceivable} instance to add.
	 */
	public void addMessageReceivable(IMessageReceivable messageReceivable) {
		receiver.getMessageReceivables().add(messageReceivable);
	}

	/**
	 * Creates a new {@link MessageSender} thread and sends {@link Message}
	 * instance over network via this thread.
	 * 
	 * @param message
	 *            given {@link Message} instance to send.
	 */
	public void sendMessage(Message message) {
		// create a new message sender thread.
		MessageSender sender = new MessageSender(message);
		// open a session and send the message.
		sender.start();
	}
}
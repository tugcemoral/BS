package tr.edu.ege.sst.infrastructure;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

/**
 * Message receiver thread gets messages asynchronously.
 */
public class MessageReceiver extends Thread {

	/**
	 * Message receivables of this thread. They are notified when a message is
	 * received.
	 */
	private List<IMessageReceivable> messageReceivables;

	/**
	 * Server socket to accept incoming messages.
	 */
	private ServerSocket serverSocket;

	/**
	 * Incoming message from network.
	 */
	private Message incomingMessage;

	/**
	 * Creates an instance of this thread to receive messages.
	 * 
	 * @param socket
	 *            server socket to accept messages.
	 */
	public MessageReceiver(ServerSocket socket) {
		this.serverSocket = socket;
	}

	/**
	 * Creates and returns message receivables list.
	 * 
	 * @return message receivables list.
	 */
	public List<IMessageReceivable> getMessageReceivables() {
		if (messageReceivables == null) {
			messageReceivables = new Vector<IMessageReceivable>();
		}
		return messageReceivables;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			while (true) {
				// wait till a socket receive.
				Socket socket = serverSocket.accept();

				if (socket.isConnected()) {
					// create an input stream to get message.
					ObjectInputStream in = new ObjectInputStream(socket
							.getInputStream());
					// read incoming object.
					this.incomingMessage = (Message) in.readObject();
					// notify that message is received.
					notifyMessageReceived();
					// close connections.
					in.close();
					socket.close();
				}
			}
		} catch (EOFException eofException) {
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Notifies to listeners that a new message is received over network.
	 */
	private void notifyMessageReceived() {
		Message message = this.incomingMessage;
		// iterates message receivables list and notifies the new message.
		for (IMessageReceivable messageReceivable : this
				.getMessageReceivables()) {
			messageReceivable.messageReceived(message);
		}
	}

}

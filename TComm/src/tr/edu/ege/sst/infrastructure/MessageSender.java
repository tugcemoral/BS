package tr.edu.ege.sst.infrastructure;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Message sender thread sends given message asynchronously.
 */
public class MessageSender extends Thread {

	/**
	 * Socket to send message.
	 */
	private Socket socket;

	/**
	 * Message to send.
	 */
	private Message message;

	/**
	 * Creates a new {@link MessageSender} thread instance to send given message
	 * asynchronously.
	 * 
	 * @param message
	 *            given {@link Message} instance to send over network.
	 */
	public MessageSender(Message message) {
		this.message = message;
		// create the connection.
		createConnection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			// create an output stream from socket's output.
			ObjectOutputStream out = new ObjectOutputStream(socket
					.getOutputStream());
			// write message to stream.
			out.writeObject(message);
			// close connections.
			socket.close();
			out.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates and opens a socket to use while connecting.
	 */
	private void createConnection() {
		try {
			// create and open a socket from receiver IP and port number.
			socket = new Socket(message.getReceiver(), message
					.getReceiverPortNumber());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
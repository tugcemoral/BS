package tr.edu.ege.sst.infrastructure;

import java.io.Serializable;

/**
 * This class represents a specific message that can be sent over network.
 * 
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1353598280907672863L;

	/**
	 * Sender client's IP of message.
	 */
	private String sender;

	/**
	 * Receiver client's IP of this message.
	 */
	private String receiver;

	/**
	 * Content of message.
	 */
	private String content;

	/**
	 * Receiver client's port number.
	 */
	private int receiverPortNumber;

	/**
	 * Creates a new {@link Message} instance to send over network.
	 * 
	 * @param sender
	 *            sender IP.
	 * @param receiver
	 *            receiver IP.
	 * @param content
	 *            the string content of message.
	 * @param receiverPortNumber
	 *            receiver port number.
	 */
	public Message(String sender, String receiver, String content,
			int receiverPortNumber) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.receiverPortNumber = receiverPortNumber;
	}

	/* Mutators of this class */

	public String getContent() {
		return content;
	}

	public String getReceiver() {
		return receiver;
	}

	public int getReceiverPortNumber() {
		return receiverPortNumber;
	}

	public String getSender() {
		return sender;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String message = "To: " + this.sender + "\nFrom: " + this.receiver
				+ "\nContent: " + this.content;
		return message;
	}
}

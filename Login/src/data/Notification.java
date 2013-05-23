package data;

import java.sql.Timestamp;

public class Notification {
	private String recipientEmail;
	private String senderEmail;
	private Timestamp timeStamp;
	private String message;
	private String action;
	private String status;
	private boolean isRead;

	/**
	 * @param recipientEmail
	 * @param senderEmail
	 * @param timeStamp
	 * @param message
	 * @param action
	 * @param status
	 * @param isRead
	 */
	public Notification(String recipientEmail, String senderEmail,
			Timestamp timeStamp, String message, String action, String status,
			boolean isRead) {
		this.recipientEmail = recipientEmail;
		this.senderEmail = senderEmail;
		this.timeStamp = timeStamp;
		this.message = message;
		this.action = action;
		this.status = status;
		this.isRead = isRead;
	}

	/**
	 * @return the recipientEmail
	 */
	public String getRecipientEmail() {
		return recipientEmail;
	}

	/**
	 * @param recipientEmail
	 *            the recipientEmail to set
	 */
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	/**
	 * @return the senderEmail
	 */
	public String getSenderEmail() {
		return senderEmail;
	}

	/**
	 * @param senderEmail
	 *            the senderEmail to set
	 */
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	/**
	 * @return the timeStamp
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the isRead
	 */
	public boolean isRead() {
		return isRead;
	}

	/**
	 * @param isRead
	 *            the isRead to set
	 */
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

}

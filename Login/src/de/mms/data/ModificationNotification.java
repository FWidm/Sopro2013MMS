/**
 * 
 */
package de.mms.data;

import java.sql.Timestamp;

/**
 * @author jonas
 * 
 */
public class ModificationNotification extends Notification {

	private Modification modification;

	/**
	 * 
	 * @param recipientEmail
	 * @param senderEmail
	 * @param timeStamp
	 * @param message
	 * @param action
	 * @param status
	 * @param isRead
	 * @param modification
	 */
	public ModificationNotification(String recipientEmail, String senderEmail,
			Timestamp timeStamp, String message, String action, String status,
			boolean isReadSender, boolean isReadRecipient, Modification modification) {
		super(recipientEmail, senderEmail, timeStamp, message, action, status,
				isReadSender, isReadRecipient);
		this.modification = modification;
	}

	/**
	 * @return the modification
	 */
	public Modification getModification() {
		return modification;
	}

	/**
	 * @param modification
	 *            the modification to set
	 */
	public void setModification(Modification modification) {
		this.modification = modification;
	}

}

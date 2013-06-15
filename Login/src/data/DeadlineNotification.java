package data;

import java.sql.Timestamp;

public class DeadlineNotification extends Notification {

	private Timestamp deadline;
	private String modManTitle;
	
	public DeadlineNotification(String recipientEmail, String senderEmail,
			Timestamp timeStamp, String message, String action, String status,
			boolean isReadSender, boolean isReadRecipient, Timestamp deadline, String modManTitle) {
		super(recipientEmail, senderEmail, timeStamp, message, action, status,
				isReadSender, isReadRecipient);
		setDeadline(deadline);
		setModManTitle(modManTitle);
		setStatus("deadline");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the deadline
	 */
	public Timestamp getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the modManTitle
	 */
	public String getModManTitle() {
		return modManTitle;
	}

	/**
	 * @param modManTitle the modManTitle to set
	 */
	public void setModManTitle(String modManTitle) {
		this.modManTitle = modManTitle;
	}

}

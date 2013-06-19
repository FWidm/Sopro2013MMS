package de.mms.data;

public class ModManAccess {
	
	private String email;
	private String modManTitle;
	
	/**
	 * @param email
	 * @param modManTitle
	 */
	public ModManAccess(String email, String modManTitle) {
		super();
		this.email = email;
		this.modManTitle = modManTitle;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/**
	 * @return String representation of the ModManAccess object
	 */
	@Override
	public String toString() {
		return "ModManAccess [email=" + email + ", modManTitle=" + modManTitle
				+ "]";
	}
	
	
	
	
}

package de.mms.data;

public class ModAccess {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModAccess [modTitle=" + modTitle + ", email=" + email + "]";
	}
	private String modTitle;
	private String email;
	
	/**
	 * @param modTitle
	 * @param email
	 */
	public ModAccess(String modTitle, String email) {
		super();
		this.modTitle = modTitle;
		this.email = email;
	}
	/**
	 * @return the modTitle
	 */
	public String getModTitle() {
		return modTitle;
	}
	/**
	 * @param modTitle the modTitle to set
	 */
	public void setModTitle(String modTitle) {
		this.modTitle = modTitle;
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

}

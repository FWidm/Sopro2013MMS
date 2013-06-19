package de.mms.data;

import java.util.Date;

public class ModManual implements Editable {

	private String modManTitle, description, exRulesTitle;
	private Date deadline;
	
	public ModManual(){
	
	}
	
	/**
	 * Construct an object of the class 
	 * 
	 * @param modManTitle
	 * @param description
	 * @param exRulesTitle
	 * @param deadline
	 */
	public ModManual(String modManTitle, String description, String exRulesTitle, Date deadline){
		this.modManTitle = modManTitle;
		this.description = description;
		this.exRulesTitle = exRulesTitle;
		this.deadline = deadline;
	}
	
	/**
	 * @return the modManTitle
	 */
	public String getModManTitle() {
		return modManTitle;
	}

	/**
	 * @param modManTitle
	 *            the modManTitle to set
	 */
	public void setModManTitle(String modManTitle) {
		this.modManTitle = modManTitle;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the exRulesTitle
	 */
	public String getExRulesTitle() {
		return exRulesTitle;
	}

	/**
	 * @param exRulesTitle
	 *            the exRulesTitle to set
	 */
	public void setExRulesTitle(String exRulesTitle) {
		this.exRulesTitle = exRulesTitle;
	}

	/**
	 * @return the date
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "ModManual [modManTitle=" + modManTitle + ", description="
				+ description + ", exRulesTitle=" + exRulesTitle + ", deadline="
				+ deadline + "]";
	}

	@Override
	public Editable computeDifferences(Editable newEditable) {
		// TODO Auto-generated method stub
		return null;
	}

}
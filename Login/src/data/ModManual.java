package data;

import java.util.Date;

public class ModManual {

	private String modManTitle, description, exRulesTitle;
	private Date date;
	
	public ModManual(){
	
	}
	
	/**
	 * Construct an object of the class 
	 * 
	 * @param modManTitle
	 * @param description
	 * @param exRulesTitle
	 * @param date
	 */
	public ModManual(String modManTitle, String description, String exRulesTitle, Date date){
		this.modManTitle = modManTitle;
		this.description = description;
		this.exRulesTitle = exRulesTitle;
		this.date = date;
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
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ModManual [modManTitle=" + modManTitle + ", description="
				+ description + ", exRulesTitle=" + exRulesTitle + ", date="
				+ date + "]";
	}

}
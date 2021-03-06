package de.mms.data;

public class ModuleModMan {

	private String modManTitle;
	private String modTitle;
	private String exRulesTitle;

	/**
	 * 
	 * @param exRulesTitle
	 * @param modManTitle
	 * @param modTitle
	 */
	public ModuleModMan(String exRulesTitle, String modManTitle, String modTitle) {
		super();
		this.exRulesTitle = exRulesTitle;
		this.modManTitle = modManTitle;
		this.modTitle = modTitle;
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
	 * @return the modTitle
	 */
	public String getModTitle() {
		return modTitle;
	}

	/**
	 * @param modTitle
	 *            the modTitle to set
	 */
	public void setModTitle(String modTitle) {
		this.modTitle = modTitle;
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

}

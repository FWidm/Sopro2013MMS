package data;

public class Module implements Editable{
	
	private String modTitle;
	private String description;
	
	/**
	 * @param modTitle
	 * @param description
	 */
	public Module(String modTitle, String description) {
		super();
		this.modTitle = modTitle;
		this.description = description;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Editable computeDifferences(Editable newEditable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}

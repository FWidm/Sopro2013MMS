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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((modTitle == null) ? 0 : modTitle.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (modTitle == null) {
			if (other.modTitle != null)
				return false;
		} else if (!modTitle.equals(other.modTitle))
			return false;
		return true;
	}
	
	
	
	
}

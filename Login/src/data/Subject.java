package data;

public class Subject {
	
	private int version; 
	private String subTitle, modTitle, description, aim; 
	private int ects; 
	private boolean ack;
	/**
	 * @param version
	 * @param subTite
	 * @param modTitle
	 * @param description
	 * @param aim
	 * @param ects
	 * @param ack
	 */
	public Subject(int version, String subTitle, String modTitle,
			String description, String aim, int ects, boolean ack) {
		super();
		this.version = version;
		this.subTitle = subTitle;
		this.modTitle = modTitle;
		this.description = description;
		this.aim = aim;
		this.ects = ects;
		this.ack = ack;
	}
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the subTite
	 */
	public String getSubTitle() {
		return subTitle;
	}
	/**
	 * @param subTite the subTite to set
	 */
	public void setSubTite(String subTite) {
		this.subTitle = subTite;
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
	/**
	 * @return the aim
	 */
	public String getAim() {
		return aim;
	}
	/**
	 * @param aim the aim to set
	 */
	public void setAim(String aim) {
		this.aim = aim;
	}
	/**
	 * @return the ects
	 */
	public int getEcts() {
		return ects;
	}
	/**
	 * @param ects the ects to set
	 */
	public void setEcts(int ects) {
		this.ects = ects;
	}
	/**
	 * @return the ack
	 */
	public boolean isAck() {
		return ack;
	}
	/**
	 * @param ack the ack to set
	 */
	public void setAck(boolean ack) {
		this.ack = ack;
	} 
	
	
	
}

package data;

/**
 * @author Fabian
 *
 */
public class Field implements Editable {
	//TODO Achtung namen von DB != namen in der Klasse evtl Probelem
	private String SubjectmodTitle;
	private int Subjectversion;
	private String SubjectsubTitle;
	private String fieldTitle;
	private String description;
	
	
	/**
	 * @param subjectmodTitle
	 * @param subjectversion
	 * @param subjectsubTitle
	 * @param fieldTitle
	 * @param description
	 */
	public Field(String fieldTitle, int subjectversion,
			String subjectsubTitle, String subjectmodTitle, String description) {
		super();
		this.SubjectmodTitle = subjectmodTitle;
		this.Subjectversion = subjectversion;
		this.SubjectsubTitle = subjectsubTitle;
		this.fieldTitle = fieldTitle;
		this.description = description;
	}


	/**
	 * @return the subjectmodTitle
	 */
	public String getSubjectmodTitle() {
		return SubjectmodTitle;
	}


	/**
	 * @param subjectmodTitle the subjectmodTitle to set
	 */
	public void setSubjectmodTitle(String subjectmodTitle) {
		SubjectmodTitle = subjectmodTitle;
	}


	/**
	 * @return the subjectversion
	 */
	public int getSubjectversion() {
		return Subjectversion;
	}


	/**
	 * @param subjectversion the subjectversion to set
	 */
	public void setSubjectversion(int subjectversion) {
		Subjectversion = subjectversion;
	}


	/**
	 * @return the subjectsubTitle
	 */
	public String getSubjectsubTitle() {
		return SubjectsubTitle;
	}


	/**
	 * @param subjectsubTitle the subjectsubTitle to set
	 */
	public void setSubjectsubTitle(String subjectsubTitle) {
		SubjectsubTitle = subjectsubTitle;
	}


	/**
	 * @return the fieldTitle
	 */
	public String getFieldTitle() {
		return fieldTitle;
	}


	/**
	 * @param fieldTitle the fieldTitle to set
	 */
	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle = fieldTitle;
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
				+ ((SubjectmodTitle == null) ? 0 : SubjectmodTitle.hashCode());
		result = prime * result
				+ ((SubjectsubTitle == null) ? 0 : SubjectsubTitle.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((fieldTitle == null) ? 0 : fieldTitle.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * true if all is equal except the version
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (SubjectmodTitle == null) {
			if (other.SubjectmodTitle != null)
				return false;
		} else if (!SubjectmodTitle.equals(other.SubjectmodTitle))
			return false;
		if (SubjectsubTitle == null) {
			if (other.SubjectsubTitle != null)
				return false;
		} else if (!SubjectsubTitle.equals(other.SubjectsubTitle))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fieldTitle == null) {
			if (other.fieldTitle != null)
				return false;
		} else if (!fieldTitle.equals(other.fieldTitle))
			return false;
		return true;
	}
	
	
	
	
}

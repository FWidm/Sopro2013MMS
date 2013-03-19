package data;
/*
 *  Class Field
 *  Fabian Widmann
 */
public class Field {
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
	public Field(String subjectmodTitle, int subjectversion,
			String subjectsubTitle, String fieldTitle, String description) {
		super();
		SubjectmodTitle = subjectmodTitle;
		Subjectversion = subjectversion;
		SubjectsubTitle = subjectsubTitle;
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
	
	
	
	
}

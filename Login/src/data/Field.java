package data;

/**
 * @author Fabian
 *
 */
public class Field {
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
	
	
	
	
}

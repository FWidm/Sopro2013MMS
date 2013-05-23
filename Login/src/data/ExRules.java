package data;

public class ExRules implements Editable {

	private String exRulesTitle;
	
	/**
	 * @param exRulesTitle
	 */
	public ExRules(String exRulesTitle) {
		super();
		this.exRulesTitle = exRulesTitle;
	}

	/**
	 * @return the exRulesTitle
	 */
	public String getExRulesTitle() {
		return exRulesTitle;
	}

	/**
	 * @param exRulesTitle the exRulesTitle to set
	 */
	public void setExRulesTitle(String exRulesTitle) {
		this.exRulesTitle = exRulesTitle;
	}

	@Override
	public Editable computeDifferences(Editable newEditable) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}

/**
 * 
 */
package de.mms.data;

/**
 * @author jonas
 * 
 */
public class Modification implements Editable {
	Editable before;
	Editable after;

	/**
	 * @param before
	 * @param after
	 */
	public Modification(Editable before, Editable after) {
		super();
		this.before = before;
		this.after = after;
	}

	/**
	 * @return the before
	 */
	public Editable getBefore() {
		return before;
	}

	/**
	 * @return the after
	 */
	public Editable getAfter() {
		return after;
	}

	@Override
	public Editable computeDifferences(Editable newEditable) {
		// TODO Auto-generated method stub
		return null;
	}

}

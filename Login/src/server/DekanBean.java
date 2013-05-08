package server;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ctrl.DBModManual;
import data.ModManual;



//Changed to Sessionscope - if in doubt revert to RequestScoped
@ManagedBean(name = "DekanBean")
@SessionScoped
public class DekanBean {
	
	private List<ModManual> modManualList;
	private LoginBean login;
	private boolean success = true;
	private String modManTitle, description;
	private int exRulesTitle;
	private String deadline;
	private Date date;
	
	// 
	SimpleDateFormat sdf;
	
	/**
	 * Equivalent to a constructor
	 */
	@PostConstruct
	public void init() {
		// is used to parse date
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		modManualList = new LinkedList<ModManual>();
		modManualList = DBModManual.loadAllModManuals();
		login = findBean("LoginBean");
	}
	
	/**
	 * finds the bean with corresponding name
	 * 
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context,
				"#{" + beanName + "}", Object.class);
	}
	
	public void resetFields() {
		modManTitle = description = null;
		deadline = null;
		exRulesTitle = -1;
	}
	
	/**
	 * saves the ModManual to the database
	 * 
	 * @param email
	 * @return boolean
	 */
	public void saveModManual(ActionEvent action) {
		success = true;
		if (modManTitle.isEmpty() || description.isEmpty() || exRulesTitle == -1 || date == null) {
			success = false;
			addErrorMessage("Empty Field error: ", "Fields may not be empty - be sure to edit every field.");
			System.out.println(success);			
			return;
		}	

		if (DBModManual.loadModManual(modManTitle) == null) {
			// parse date to sql compatible format
			deadline = sdf.format(date);
			String exRules = decodeExRules();
			DBModManual.saveModManual(new ModManual(modManTitle, description, exRules, deadline));
			System.out.println(success);
			addMessage("Module manual created: ", "" + modManTitle + ", " + exRules + ", " + deadline);
			return;
		}
		
		success = false;
		System.out.println("error - modMantitle already exists: " + modManTitle + " " + success);
		addErrorMessage("Module manual title already exists: ", "'" + modManTitle + "' is already in the database - please doublecheck.");
	}
	
	/**
	 * function that refreshes the ModManualsList for and edit mode
	 */
	public void actualizeModManualList() {
		String exRules = decodeExRules();
		if (exRules == null)
			setModManualList(DBModManual.loadAllModManuals());
		else
			setModManualList(DBModManual.loadModManuals(exRules));
	}
	
	/**
	 * Translates numbers into Strings that are way more readable
	 * 
	 * @param rol
	 * @return
	 */
	private String decodeExRules() {
		switch (exRulesTitle) {
		case 1:
			return "PO2010";
		case 2:
			return "PO2012";
		default:
			return null;
		}
	}
	
	/**
	 * if anything goes wrong - display an Error
	 * 
	 * @param title
	 * @param msg
	 */
	public void addErrorMessage(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage("dekan-messages",
				new FacesMessage(FacesMessage.SEVERITY_FATAL, title, msg));
	}

	/**
	 * Informs the User via message.
	 * 
	 * @param title
	 * @param msg
	 */
	public void addMessage(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage("dekan-messages",
				new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
	}
	
	/**
	 * @return the modManualList
	 */
	public List<ModManual> getModManualList() {
		return modManualList;
	}

	/**
	 * @param modManualList the modManualList to set
	 */
	public void setModManualList(List<ModManual> modManualList) {
		this.modManualList = modManualList;
	}

	/**
	 * @return the modManTitle
	 */
	public String getModManTitle() {
		return modManTitle;
	}

	/**
	 * @param modManTitle the modManTitle to set
	 */
	public void setModManTitle(String modManTitle) {
		this.modManTitle = modManTitle;
	}


	/**
	 * @return the deadline
	 */
	public String getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @return the exRulesTitle
	 */
	public int getExRulesTitle() {
		return exRulesTitle;
	}

	/**
	 * @param exRulesTitle the exRulesTitle to set
	 */
	public void setExRulesTitle(int exRulesTitle) {
		this.exRulesTitle = exRulesTitle;
	}

	

	
	
}

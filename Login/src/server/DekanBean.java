package server;

import java.sql.Timestamp;
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
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import data.DeadlineNotification;
import data.ExRules;
import data.ModManual;
import db.DBExRules;
import db.DBModManual;
import db.DBNotification;
import db.DBUser;

@ManagedBean(name = "DekanBean")
@SessionScoped
public class DekanBean {

	private List<ModManual> modManualList;
	private boolean success = true;
	private String modManTitle, description;
	private int exRulesTitle;
	private Date deadline;
	private ModManual currentMM;

	private List<ExRules> exRulesList;
	private List<ExRules> exRulesSearchList;
	private String exRules;
	private ExRules currentER;

	private String currentUser;

	private static SimpleDateFormat sdf;

	/**
	 * Equivalent to a constructor
	 */
	@PostConstruct
	public void init() {
		// get User Session
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		currentUser = (String) session.getAttribute("email");

		sdf = new SimpleDateFormat("yyyy-MM-dd");
		modManualList = new LinkedList<ModManual>();
		modManualList = DBModManual.loadAllModManuals();
		exRulesList = new LinkedList<ExRules>();
		exRulesList = DBExRules.loadAllExRules();
		exRulesSearchList = new LinkedList<ExRules>();
		exRulesSearchList = DBExRules.loadAllExRules();
	}

	/**
	 * reset modManTitle, description, deadline and exRulesTitle
	 */
	public void resetFields() {
		modManTitle = description = null;
		deadline = null;
		exRules = null;
	}

	/**
	 * reset exRules to null
	 */
	public void resetField() {
		exRules = null;
	}

	/**
	 * saves the ModManual to the database
	 * 
	 */
	public void saveModManual(ActionEvent action) {
		success = true;
		if (modManTitle.isEmpty() || description.isEmpty() || exRules == null
				|| exRules.equals("") || deadline == null) {
			success = false;
			addErrorMessage("Fehler: ",
					"Bitte f\u00FCllen Sie alle Felder aus.");
			System.out.println(success);
			return;
		}

		if (DBModManual.loadModManual(modManTitle) == null) {
			DBModManual.saveModManual(new ModManual(modManTitle, description,
					exRules, deadline));
			System.out.println(success);
			addMessage("Modulhandbuch wurde erstellt: ", "" + modManTitle + ", "
					+ exRules + ", " + deadline);
			exRules = null;
			actualizeModManualList();

			/**
			 * create DeadlineNotifications
			 */
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Timestamp date = new Timestamp(deadline.getTime());
			List<String> mailAdresses = new LinkedList<String>();
			List<String> modEx = DBUser.loadUsersEmailByRole("Redakteur");
			List<String> redak = DBUser
					.loadUsersEmailByRole("Modulverantwortlicher");
			mailAdresses.addAll(modEx);
			mailAdresses.addAll(redak);

			for (int i = 0; i < mailAdresses.size(); i++) {
				DBNotification.saveNotification(new DeadlineNotification(
						mailAdresses.get(i), currentUser, timestamp, "", "",
						"queued", false, false, date, modManTitle));
			}
			
			resetFields();
			return;
		}

		success = false;
		System.out.println("error - modMantitle already exists: " + modManTitle
				+ " " + success);
		addErrorMessage("Fehler: ", "'"
				+ modManTitle
				+ "' ist bereits in der Datenbank gespeichert.");
	}

	/**
	 * saves the examination rules to the database
	 * 
	 */
	public void saveExRules(ActionEvent action) {
		success = true;
		System.out.println("**** " + exRules);
		if (exRules.equals("") || exRules == null) {
			success = false;
			addErrorMessage("Fehler - leeres Feld: ", "Bitte f\u00FCllen Sie alle Felder aus.");
			System.out.println(success);
			return;
		}

		if (DBExRules.loadExRules(exRules) == null) {
			DBExRules.saveExRule(new ExRules(exRules));
			addMessage("Pr\u00FCfungsordnung wurde erstellt: ", exRules);
			exRules = null;
			actualizeExRulesList();
			actualizeExRulesSearchList();
			System.out.println(success);
			return;
		}
		
		success = false;
		addErrorMessage("Fehler - Pr\u00FCfungsordnung existiert bereits: ", "'"
				+ exRules
				+ "' ist bereits in der Datenbank gespeichert.");
	}

	/**
	 * function that refreshes the ModManualsList for delete and edit mode
	 */
	public void actualizeModManualList() {
		if (exRules == null || exRules.equals(""))
			setModManualList(DBModManual.loadAllModManuals());
		else
			setModManualList(DBModManual.loadModManuals(exRules));
	}

	/**
	 * Event Triggered when the accept icon is clicked on edit module manual tab
	 * - updates with new Values
	 * 
	 * @param event
	 */
	public void onEdit(RowEditEvent event) {

		ModManual m = (ModManual) event.getObject();
		FacesMessage msg = new FacesMessage("Modulhandbuch wurde ge\u00E4ndert:",
				((ModManual) event.getObject()).getModManTitle().toString());
		if (DBModManual.loadModManual(m.getModManTitle()) != null) {

			// if deadline was changed --> notify
			if (!((DBModManual.loadModManual(m.getModManTitle())).getDeadline()
					.toString().equals(sdf.format(m.getDeadline())))) {
				System.out.println("date changed!");
				
				/**
				 * create DeadlineNotifications
				 */
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Timestamp date = new Timestamp(deadline.getTime());
				List<String> mailAdresses = new LinkedList<String>();
				List<String> modEx = DBUser.loadUsersEmailByRole("Redakteur");
				List<String> redak = DBUser
						.loadUsersEmailByRole("Modulverantwortlicher");
				mailAdresses.addAll(modEx);
				mailAdresses.addAll(redak);

				for (int i = 0; i < mailAdresses.size(); i++) {
					DBNotification.saveNotification(new DeadlineNotification(
							mailAdresses.get(i), currentUser, timestamp, "", "",
							"queued", false, false, date, modManTitle));
				}
				
			}
			DBModManual.updateModManual(m, m.getModManTitle());
		}
		FacesContext.getCurrentInstance().addMessage("edit-messages", msg);
		actualizeModManualList();
	}

	/**
	 * Event triggered when editing user is cancelled
	 * 
	 * @param event
	 */
	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Bearbeiten abgebrochen:",
				((ModManual) event.getObject()).getModManTitle());
		FacesContext.getCurrentInstance().addMessage("edit-messages", msg);
	}

	/**
	 * function that refreshes the exRulesList for delete edit mode
	 */
	public void actualizeExRulesList() {
		System.out.println(exRules);
		if (exRules == null || exRules.equals(""))
			setExRulesList(DBExRules.loadAllExRules());
		else
			setExRulesList(DBExRules.loadAllExRules(exRules));
	}

	/**
	 * function that refreshes the exRulesSearchList for the drop down menu
	 */
	public void actualizeExRulesSearchList() {
		setExRulesSearchList(DBExRules.loadAllExRules());
	}

	/**
	 * deletes the exRule from the database, which was selected in the data
	 * table
	 */
	public void deleteModManual() {
		DBModManual.deleteModManual(currentMM.getModManTitle());
		exRules = null;
		actualizeModManualList();
	}

	/**
	 * deletes the exRule from the database, which was selected in the data
	 * table
	 */
	public void deleteExRules() {
		DBExRules.deleteExRule(currentER.getExRulesTitle());
		exRules = null;
		actualizeExRulesList();
		actualizeExRulesSearchList();
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
	 * @param modManualList
	 *            the modManualList to set
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
	 * @param modManTitle
	 *            the modManTitle to set
	 */
	public void setModManTitle(String modManTitle) {
		this.modManTitle = modManTitle;
	}

	/**
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline
	 *            the deadline to set
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
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
	 * @param exRulesTitle
	 *            the exRulesTitle to set
	 */
	public void setExRulesTitle(int exRulesTitle) {
		this.exRulesTitle = exRulesTitle;
	}

	/**
	 * @return the exRules
	 */
	public String getExRules() {
		return exRules;
	}

	/**
	 * @param exRules
	 *            the exRules to set
	 */
	public void setExRules(String exRules) {
		this.exRules = exRules;
	}

	/**
	 * @return the exRulesList
	 */
	public List<ExRules> getExRulesList() {
		return exRulesList;
	}

	/**
	 * @param exRulesList
	 *            the exRulesList to set
	 */
	public void setExRulesList(List<ExRules> exRulesList) {
		this.exRulesList = exRulesList;
	}

	/**
	 * @return the currentER
	 */
	public ExRules getCurrentER() {
		return currentER;
	}

	/**
	 * @param currentER
	 *            the currentER to set
	 */
	public void setCurrentER(ExRules currentER) {
		this.currentER = currentER;
	}

	/**
	 * @return the currentMM
	 */
	public ModManual getCurrentMM() {
		return currentMM;
	}

	/**
	 * @param currentMM
	 *            the currentMM to set
	 */
	public void setCurrentMM(ModManual currentMM) {
		this.currentMM = currentMM;
	}

	/**
	 * @return the exRulesSearchList
	 */
	public List<ExRules> getExRulesSearchList() {
		return exRulesSearchList;
	}

	/**
	 * @param exRulesSearchList
	 *            the exRulesSearchList to set
	 */
	public void setExRulesSearchList(List<ExRules> exRulesSearchList) {
		this.exRulesSearchList = exRulesSearchList;
	}

}

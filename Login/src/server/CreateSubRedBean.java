package server;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import ctrl.CreateSubRedActionListener;


import data.ExRules;
import data.Field;
import data.ModManual;
import data.Modification;
import data.ModificationNotification;
import data.Module;
import data.Subject;
import db.DBExRules;
import db.DBField;
import db.DBModManAccess;
import db.DBNotification;
import db.DBSubject;
import db.DBUser;

@ManagedBean(name = "CreateSubRedBean")
@SessionScoped
public class CreateSubRedBean {

	public static final String PRUEFORDNUNG = "Pr\u00FCfungsordnungen";
	public static final String MODMANUAL = "Modulhandb\u00FCcher";
	public static final String MODULE = "Module";
	public static final String FAECHER = "F\u00E4cher";
	public static final String TO_FOR = "message-log-create";

	// Add your tag id's for ajax-update on menuItemClick here.
	public static final String UPDATE_AJAX = "list-menu-create back-menu-create scrollPanel-create saveSub";

	private String exRules, modMan, module;
	private List<ExRules> exRulesList;
	private List<ModManual> modManList;
	private List<Module> moduleList;
	private MenuModel model, backModel;
	private String title;

	private boolean editable;

	private String currentUser;
	private List<String> userRights;
	private boolean hasPermission;

	private String subTitle, ects, aim, description;
	private List<Field> fieldList;
	private boolean emptyFieldList;

	public CreateSubRedBean() {

		// init
		// get User Session
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		currentUser = (String) session.getAttribute("email");

		// Initialize Rights
		userRights = DBModManAccess.loadModuleModManTitleAccess(currentUser);
		hasPermission = false;
		emptyFieldList = true;

		// set editable
		editable = false;

		// init fieldList
		fieldList = new LinkedList<Field>();

		// continue menu
		backModel = new DefaultMenuModel();

		model = new DefaultMenuModel();
		Submenu submenu = new Submenu();
		submenu.setLabel(PRUEFORDNUNG);

		exRulesList = DBExRules.loadAllExRules();

		for (int i = 0; i < exRulesList.size(); i++) {
			MenuItem m = new MenuItem();
			m.setValue(exRulesList.get(i).getExRulesTitle());
			m.setAjax(true);
			m.setUpdate(UPDATE_AJAX);
			m.addActionListener(new CreateSubRedActionListener());
			submenu.getChildren().add(m);
		}
		model.addSubmenu(submenu);
	}

	/**
	 * this method is called when clicking a Subject in the menu parameter is
	 * the clicked subject
	 * 
	 * @param sub
	 */
	public void handleSubject(Subject sub) {
		// TODO Paste your event handling here
		// should not happen
		System.err.println("should not happen: handleSubject");
	}

	/**
	 * this method is called when clicking a Module in the menu parameter is the
	 * clicked module
	 * 
	 * @param mod
	 */
	public void handleModule(Module mod) {
		// TODO Paste your event handling here
		title = mod.getModTitle();

		// set editable
		editable = true;

		// Only Test and can be removed
		System.out.println(mod.getModTitle());
		System.out.println(mod.getDescription());
	}

	/**
	 * this method is called when clicking a ModManual in the menu parameter is
	 * the clicked modManual
	 * 
	 * @param modMan
	 */
	public void handleModManual(ModManual modMan) {
		// TODO Paste your event handling here
		title = modMan.getModManTitle();

		// set editable
		editable = false;
		// check Access
		boolean found = false;
		for (int i = 0; i < userRights.size(); i++) {
			System.err.println(userRights.get(i));
			if (userRights.get(i).equals(modMan.getModManTitle())) {
				found = true;
				break;
			}
		}
		if (found) {
			hasPermission = true;
		} else {
			hasPermission = false;
		}

		// Only Test and can be removed
		System.out.println(modMan.getModManTitle());

	}

	/**
	 * this method is called when clicking a ExRule in the menu parameter is the
	 * clicked exRule
	 * 
	 * @param rule
	 */
	public void handleExRule(ExRules rule) {
		// TODO Paste your event handling here
		title = rule.getExRulesTitle();

		// set editable
		editable = false;
		hasPermission = false;

		// Only Test and can be removed
		System.out.println(rule.getExRulesTitle());
	}

	/**
	 * Adds a new field to fieldList
	 */
	public void addField(int index) {
		if (!emptyFieldList) {
			fieldList.add(index + 1, new Field("", 1, "", module, ""));
		} else {
			fieldList.add(new Field("", 1, "", module, ""));
		}

		emptyFieldList = false;
	}

	/**
	 * removes a field from fieldList
	 */
	public void removeField(int index) {
		fieldList.remove(index);
		if (fieldList.size() == 0)
			emptyFieldList = true;
	}

	public void accept() {
		boolean empty = false;
		for (int i = 0; i < fieldList.size(); i++) {
			if (fieldList.get(i).getFieldTitle().equals("")
					|| fieldList.get(i).getFieldTitle() == null) {
				empty = true;
				break;
			}
		}
		if (DBSubject.loadSubject(subTitle, module).size() != 0) {
			addErrorMessage(TO_FOR, "Fehler: ", "Das Fach existiert bereits.");
		} else if (subTitle.equals("") || subTitle == null || empty) {
			addErrorMessage(TO_FOR, "Fehler: ",
					"Bitte tragen Sie einen Titel für Ihr Fach bzw. Zusatzfeld ein.");
		} else {
			Subject sub = new Subject(1, subTitle, module, description, aim,
					Integer.valueOf(ects), false);
			try {
				DBSubject.saveSubject(sub);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < fieldList.size(); i++) {
				fieldList.get(i).setSubjectmodTitle(module);
				fieldList.get(i).setSubjectsubTitle(subTitle);
				try {
					DBField.saveField(fieldList.get(i));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			List<String> dezernatList = DBUser
					.loadAllUserEmailsByRole("Dezernat");

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			for (int i = 0; i < dezernatList.size(); i++) {
				ModificationNotification mn = new ModificationNotification(
						dezernatList.get(i), currentUser, timestamp, "",
						"edit", "queued", false, true, new Modification(sub, sub));
				DBNotification.saveNotification(mn);
			}
			
			addMessage(TO_FOR, "Erfolg: ",
					"Das Fach wurde erfolgreich gespeichert und zur Prüfung verschickt.");

		}

	}

	/**
	 * if anything goes wrong - display an Error
	 * 
	 * @param title
	 * @param msg
	 */
	public void addErrorMessage(String toFor, String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(toFor,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, title, msg));
	}

	/**
	 * Informs the User via message.
	 * 
	 * @param title
	 * @param msg
	 */
	public void addMessage(String toFor, String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(toFor,
				new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
	}

	/**
	 * @return the backModel
	 */
	public MenuModel getBackModel() {
		return backModel;
	}

	/**
	 * @param backModel
	 *            the backModel to set
	 */
	public void setBackModel(MenuModel backModel) {
		this.backModel = backModel;
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
	 * @return the modMan
	 */
	public String getModMan() {
		return modMan;
	}

	/**
	 * @param modMan
	 *            the modMan to set
	 */
	public void setModMan(String modMan) {
		this.modMan = modMan;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(String module) {
		this.module = module;
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
	 * @return the modManList
	 */
	public List<ModManual> getModManList() {
		return modManList;
	}

	/**
	 * @param modManList
	 *            the modManList to set
	 */
	public void setModManList(List<ModManual> modManList) {
		this.modManList = modManList;
	}

	/**
	 * @return the moduleList
	 */
	public List<Module> getModuleList() {
		return moduleList;
	}

	/**
	 * @param moduleList
	 *            the moduleList to set
	 */
	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	/**
	 * @return the model
	 */
	public MenuModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(MenuModel model) {
		this.model = model;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the currentUser
	 */
	public String getCurrentUser() {
		return currentUser;
	}

	/**
	 * @return the userRights
	 */
	public List<String> getUserRights() {
		return userRights;
	}

	/**
	 * @return the hasPermission
	 */
	public boolean isHasPermission() {
		return hasPermission;
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle
	 *            the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return the ects
	 */
	public String getEcts() {
		return ects;
	}

	/**
	 * @param ects
	 *            the ects to set
	 */
	public void setEcts(String ects) {
		this.ects = ects;
	}

	/**
	 * @return the aim
	 */
	public String getAim() {
		return aim;
	}

	/**
	 * @param aim
	 *            the aim to set
	 */
	public void setAim(String aim) {
		this.aim = aim;
	}

	/**
	 * @return the fieldList
	 */
	public List<Field> getFieldList() {
		return fieldList;
	}

	/**
	 * @param fieldList
	 *            the fieldList to set
	 */
	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	/**
	 * @return the emptyFieldList
	 */
	public boolean isEmptyFieldList() {
		return emptyFieldList;
	}

	/**
	 * @param emptyFieldList
	 *            the emptyFieldList to set
	 */
	public void setEmptyFieldList(boolean emptyFieldList) {
		this.emptyFieldList = emptyFieldList;
	}

}

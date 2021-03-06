package de.mms.server;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;


import de.mms.ctrl.DezernatVersionActionListener;
import de.mms.data.ExRules;
import de.mms.data.Field;
import de.mms.data.ModManual;
import de.mms.data.Module;
import de.mms.data.Subject;
import de.mms.db.DBExRules;
import de.mms.db.DBField;
import de.mms.db.DBSubject;

@ManagedBean(name = "DezernatVersionBean")
@SessionScoped
public class DezernatVersionBean {

	public static final String PRUEFORDNUNG = "Pr\u00FCfungsordnungen";
	public static final String MODMANUAL = "Modulhandb\u00FCcher";
	public static final String MODULE = "Module";
	public static final String FAECHER = "F\u00E4cher";
	
	public static final String TO_FOR = "message-log-dez";

	// Add your tag id's for ajax-update on menuItemClick here.
	public static final String UPDATE_AJAX = "list-menu-version back-menu-version scrollPanel-version";

	private String exRules, modMan, module;
	private List<ExRules> exRulesList;
	private List<ModManual> modManList;
	private List<Module> moduleList;
	private List<Subject> subjectList;
	private MenuModel model, backModel;

	private String title, description, ects, aim;
	private boolean mainVisible, ectsAimVisible, addInfoVisible;
	List<Field> fieldList, fieldList2;

	// Versions Variables
	private Subject old, current;

	public DezernatVersionBean() {
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
			m.addActionListener(new DezernatVersionActionListener());
			submenu.getChildren().add(m);
		}
		model.addSubmenu(submenu);

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

	public void changeSubjectsVersion() {
		if (old == null) {
			addErrorMessage(TO_FOR, "Es gibt keine alte Version:",
					"Es wurde keine Änderung vorgenommen.");
		} else if (DBSubject.loadSubject(current.getVersion() + 1,
				current.getSubTitle(), current.getModTitle()) != null) {
			addErrorMessage(TO_FOR, "Es existiert eine unbestätigte Änderung:",
					"Es wurde keine Änderung vorgenommen.");
		} else {
			title = old.getSubTitle();
			description = old.getDescription();
			ects = String.valueOf(old.getEcts());
			aim = old.getAim();

			DBField.deleteFields(current.getVersion(), current.getSubTitle(),
					current.getModTitle());
			DBField.deleteFields(old.getVersion(), old.getSubTitle(),
					old.getModTitle());
			DBSubject.deleteSubject(current);
			DBSubject.deleteSubject(old);

			try {
				current.setVersion(old.getVersion());
				DBSubject.saveSubject(current);

				old.setVersion(old.getVersion() + 1);
				DBSubject.saveSubject(old);

				for (Field f : fieldList) {
					f.setSubjectversion(current.getVersion());
					DBField.saveField(f);
				}

				for (Field f : fieldList2) {
					f.setSubjectversion(old.getVersion());
					DBField.saveField(f);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// set new attributes
			current = old.getCopy();
			old = DBSubject.loadVersionBefore(current);
			fieldList = DBField.loadFieldList(current.getModTitle(),
					current.getVersion(), current.getSubTitle());
			fieldList2 = DBField.loadFieldList(old.getModTitle(),
					old.getVersion(), old.getSubTitle());
			
			addMessage(TO_FOR, "Erfolgreich:",
					"Die Vorgängerversion würde wieder hergestellt.");
		}
	}

	/**
	 * this method is called when clicking a Subject in the menu parameter is
	 * the clicked subject
	 * 
	 * @param sub
	 */
	public void handleSubject(Subject sub) {
		// TODO Paste your event handling here
		current = sub;
		old = DBSubject.loadVersionBefore(current);
		fieldList = DBField.loadFieldList(sub.getModTitle(), sub.getVersion(),
				sub.getSubTitle());
		if (old != null)
			fieldList2 = DBField.loadFieldList(old.getModTitle(),
					old.getVersion(), old.getSubTitle());
		title = sub.getSubTitle();
		description = sub.getDescription();
		ects = String.valueOf(sub.getEcts());
		aim = sub.getAim();
		mainVisible = true;
		ectsAimVisible = true;
		addInfoVisible = true;
		// Only Test and can be removed
		System.out.println(sub.getSubTitle());
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
		description = mod.getDescription();
		mainVisible = true;
		ectsAimVisible = false;
		addInfoVisible = false;
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
		description = modMan.getDescription();
		mainVisible = true;
		ectsAimVisible = false;
		addInfoVisible = false;
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
		description = "";
		mainVisible = true;
		ectsAimVisible = false;
		addInfoVisible = false;
		// Only Test and can be removed
		System.out.println(rule.getExRulesTitle());
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
	 * @return the subjectList
	 */
	public List<Subject> getSubjectList() {
		return subjectList;
	}

	/**
	 * @param subjectList
	 *            the subjectList to set
	 */
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
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
	 * @return the mainVisible
	 */
	public boolean isMainVisible() {
		return mainVisible;
	}

	/**
	 * @param mainVisible
	 *            the mainVisible to set
	 */
	public void setMainVisible(boolean mainVisible) {
		this.mainVisible = mainVisible;
	}

	/**
	 * @return the ectsAimVisible
	 */
	public boolean isEctsAimVisible() {
		return ectsAimVisible;
	}

	/**
	 * @param ectsAimVisible
	 *            the ectsAimVisible to set
	 */
	public void setEctsAimVisible(boolean ectsAimVisible) {
		this.ectsAimVisible = ectsAimVisible;
	}

	/**
	 * @return the addInfoVisible
	 */
	public boolean isAddInfoVisible() {
		return addInfoVisible;
	}

	/**
	 * @param addInfoVisible
	 *            the addInfoVisible to set
	 */
	public void setAddInfoVisible(boolean addInfoVisible) {
		this.addInfoVisible = addInfoVisible;
	}

	/**
	 * @return the fieldList2
	 */
	public List<Field> getFieldList2() {
		return fieldList2;
	}

	/**
	 * @param fieldList2
	 *            the fieldList2 to set
	 */
	public void setFieldList2(List<Field> fieldList2) {
		this.fieldList2 = fieldList2;
	}

	/**
	 * @return the old
	 */
	public Subject getOld() {
		return old;
	}

	/**
	 * @param old
	 *            the old to set
	 */
	public void setOld(Subject old) {
		this.old = old;
	}

	/**
	 * @return the current
	 */
	public Subject getCurrent() {
		return current;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public void setCurrent(Subject current) {
		this.current = current;
	}

}

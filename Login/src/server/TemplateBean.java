package server;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import ctrl.DBExRules;
import data.ExRules;
import data.ModManual;
import data.Module;
import data.Subject;
import data.TemplateActionListener;

@ManagedBean(name="TemplateBean")
@SessionScoped
public class TemplateBean {
	
	public static final String PRUEFORDNUNG = "Prüfungsordnungen";
	public static final String MODMANUAL = "Modulhandbücher";
	public static final String MODULE = "Module";
	public static final String FAECHER = "Fächer";
	
	//Add your tag id's for ajax-update on menuItemClick here.
	public static final String UPDATE_AJAX = "list-menu back-menu scrollPanel";
	
	private String exRules, modMan, module;
	private List<ExRules> exRulesList;
	private List<ModManual> modManList;
	private List<Module> moduleList;
	private List<Subject> subjectList;
	private MenuModel model, backModel;
	
	private String title, description, aim, ects;
	private boolean titleVisible, descriptionVisible, aimVisible, ectsVisible;
	
	public TemplateBean() {
		backModel = new DefaultMenuModel();
		
		model = new DefaultMenuModel();
		Submenu submenu = new Submenu();
		submenu.setLabel(PRUEFORDNUNG);
		
		exRulesList = DBExRules.loadAllExRules();
		
		for(int i = 0; i < exRulesList.size(); i++) {
			MenuItem m = new MenuItem();
			m.setValue(exRulesList.get(i).getExRulesTitle());
			m.setAjax(true);
			m.setUpdate(UPDATE_AJAX);
			m.addActionListener(new TemplateActionListener());
			submenu.getChildren().add(m);
		}
		model.addSubmenu(submenu);
		
	}
	
	/**
	 * this method is called when clicking a Subject in the menu
	 * parameter is the clicked subject
	 * 
	 * @param sub
	 */
	public void handleSubject(Subject sub) {
		//TODO Paste your event handling here
		title = sub.getSubTitle();
		description = sub.getDescription();
		ects = String.valueOf(sub.getEcts());
		aim = sub.getAim();
		titleVisible = true;
		descriptionVisible = true;
		ectsVisible = true;
		aimVisible = true;
		//Only Test and can be removed
		System.out.println(sub.getSubTitle());
	}
	
	/**
	 * this method is called when clicking a Module in the menu
	 * parameter is the clicked module
	 * 
	 * @param mod
	 */
	public void handleModule(Module mod) {
		//TODO Paste your event handling here
		title = mod.getModTitle();
		description = mod.getDescription();
		ects = "";
		aim = "";
		titleVisible = true;
		descriptionVisible = true;
		ectsVisible = false;
		aimVisible = false;
		//Only Test and can be removed
		System.out.println(mod.getModTitle());
		System.out.println(mod.getDescription());
	}
	
	/**
	 * this method is called when clicking a ModManual in the menu
	 * parameter is the clicked modManual
	 * 
	 * @param modMan
	 */
	public void handleModManual(ModManual modMan) {
		//TODO Paste your event handling here
		title = modMan.getModManTitle();
		description = modMan.getDescription();
		ects = "";
		aim = "";
		titleVisible = true;
		descriptionVisible = true;
		ectsVisible = false;
		aimVisible = false;
		//Only Test and can be removed
		System.out.println(modMan.getModManTitle());
		
	}
	
	/**
	 * this method is called when clicking a ExRule in the menu
	 * parameter is the clicked exRule
	 * 
	 * @param rule
	 */
	public void handleExRule(ExRules rule) {
		//TODO Paste your event handling here
		title = rule.getExRulesTitle();
		description = "";
		ects = "";
		aim = "";
		titleVisible = true;
		descriptionVisible = false;
		ectsVisible = false;
		aimVisible = false;
		//Only Test and can be removed
		System.out.println(rule.getExRulesTitle());
	}
	
	/**
	 * @return the backModel
	 */
	public MenuModel getBackModel() {
		return backModel;
	}

	/**
	 * @param backModel the backModel to set
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
	 * @param exRules the exRules to set
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
	 * @param modMan the modMan to set
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
	 * @param module the module to set
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
	 * @param exRulesList the exRulesList to set
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
	 * @param modManList the modManList to set
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
	 * @param moduleList the moduleList to set
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
	 * @param model the model to set
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
	 * @param subjectList the subjectList to set
	 */
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
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
	public String getEcts() {
		return ects;
	}

	/**
	 * @param ects the ects to set
	 */
	public void setEcts(String ects) {
		this.ects = ects;
	}

	/**
	 * @return the titleVisible
	 */
	public boolean isTitleVisible() {
		return titleVisible;
	}

	/**
	 * @param titleVisible the titleVisible to set
	 */
	public void setTitleVisible(boolean titleVisible) {
		this.titleVisible = titleVisible;
	}

	/**
	 * @return the descriptionVisible
	 */
	public boolean isDescriptionVisible() {
		return descriptionVisible;
	}

	/**
	 * @param descriptionVisible the descriptionVisible to set
	 */
	public void setDescriptionVisible(boolean descriptionVisible) {
		this.descriptionVisible = descriptionVisible;
	}

	/**
	 * @return the aimVisible
	 */
	public boolean isAimVisible() {
		return aimVisible;
	}

	/**
	 * @param aimVisible the aimVisible to set
	 */
	public void setAimVisible(boolean aimVisible) {
		this.aimVisible = aimVisible;
	}

	/**
	 * @return the ectsVisible
	 */
	public boolean isEctsVisible() {
		return ectsVisible;
	}

	/**
	 * @param ectsVisible the ectsVisible to set
	 */
	public void setEctsVisible(boolean ectsVisible) {
		this.ectsVisible = ectsVisible;
	}
}

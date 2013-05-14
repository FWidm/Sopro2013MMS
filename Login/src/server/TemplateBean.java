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
	
	private String exRules, modMan, module;
	private List<ExRules> exRulesList;
	private List<ModManual> modManList;
	private List<Module> moduleList;
	private List<Subject> subjectList;
	private MenuModel model, backModel;
	
	public TemplateBean() {
		
		backModel = new DefaultMenuModel();
		
		model = new DefaultMenuModel();
		Submenu submenu = new Submenu();
		submenu.setLabel(PRUEFORDNUNG);
		
		exRulesList = DBExRules.loadExRules();
		
		for(int i = 0; i < exRulesList.size(); i++) {
			MenuItem m = new MenuItem();
			m.setValue(exRulesList.get(i).getExRulesTitle());
			m.setAjax(true);
			m.setUpdate("list-menu back-menu");
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
	
}

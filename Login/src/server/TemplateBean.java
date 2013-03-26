package server;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ctrl.DBExRules;
import ctrl.DBModManual;

import data.ExRules;
import data.ModManual;
import data.Module;

@ManagedBean(name="TemplateBean")
@RequestScoped
public class TemplateBean {
	
	private String exRules, modMan, module;
	private List<ExRules> exRulesList;
	private List<ModManual> modManList;
	private List<Module> moduleList;
	private List<String> titles;
	
	public TemplateBean() {
		titles = new LinkedList<String>();
		exRulesList = DBExRules.loadExRules();
		for(int i = 0; i < exRulesList.size(); i++) {
			titles.add(exRulesList.get(i).getExRulesTitle());
		}
	}
	
	public void loadModMan(ActionEvent event) {
		String exRulesTitle = (String)event.getComponent().getAttributes().get("action");
		System.out.println(exRulesTitle);
		titles = new LinkedList<String>();
		modManList = DBModManual.loadAllModManuals(exRulesTitle);
		for(int i = 0; i < modManList.size(); i++) {
			titles.add(modManList.get(i).getModManTitle());
		}
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
	 * @return the titles
	 */
	public List<String> getTitles() {
		return titles;
	}

	/**
	 * @param titles the titles to set
	 */
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	
}

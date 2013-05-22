package server;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DualListModel;

import ctrl.DBModAccess;
import ctrl.DBModule;
import ctrl.DBUser;

import data.ModAccess;
import data.User;

@ManagedBean(name = "AccessBean")
@SessionScoped
public class AccessBean {
	private List<String> userList;
	private List<ModAccess> modAccList;
	private List<String> modTitleList;

	private User selected;
	private String selectedUser;
	private String selectedAccess;

	public AccessBean() {
		//TODO Remove link between adam.admin and the testmodule in the database.
		userList = DBUser.loadAllUserEmailsByRole("Modulverantwortlicher");
		new LinkedList<User>();
		
		//TODO evtl abgrenzen noch vorher handbuch aussuchen? 
		modTitleList=DBModule.loadAllModuleTitles();

		System.out.println(userList.toString());
		
		for (String e: userList) {
				modAccList=DBModAccess.loadAccess(e);
				//System.out.println(u.getEmail()+":\t-"+modAccList.toString());
			}

			System.out.println(modAccList.toString());
		
	}

	public void loadAccess(ActionEvent e) {
		System.out.println("selected");
		modTitleList=DBModule.loadAllModuleTitles();
		if(selected!=null){
			System.out.println(selected.getEmail());
			modAccList=DBModAccess.loadAccess(selected.getEmail());			
		}
	}
	
	public void acceptChanges(ActionEvent e){
		//TODO: fix selectedAccess to be set on buttonclick,check if selected(user) does not already have the privileges - then save it to the DB and reload the list, return an error or info message dependant on the outcome
		System.out.println(selectedUser+"\t"+selectedAccess+" is selected");
		//modAccList=DBModAccess.loadAccess(selected.getEmail());
		//addErrorMessage("messages-access", "Failed", "An SQL Error Occured in 'updateSubjectAck'@DBSubject, call the Administrator for further help and note this message.");
	}
	
	/**
	 * if anything goes wrong - display an Error
	 * 
	 * @param title
	 * @param msg
	 */
	public void addErrorMessage(String toFor,String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(toFor,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, title, msg));
	}

	/**
	 * Informs the User via message.
	 * 
	 * @param title
	 * @param msg
	 */
	public void addMessage(String toFor,String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(toFor,
				new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
	}

	
	/**
	 * @return the modAccList
	 */
	public List<ModAccess> getModAccList() {
		return modAccList;
	}

	/**
	 * @param modAccList the modAccList to set
	 */
	public void setModAccList(List<ModAccess> modAccList) {
		this.modAccList = modAccList;
	}

	/**
	 * @return the selected
	 */
	public User getSelected() {
		System.out.println(selected==null);
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(User selected) {
		System.out.println(selected.toString());
		this.selected = selected;
	}

	/**
	 * @return the modAcc
	 */
	public List<String> getModTitleList() {
		return modTitleList;
	}

	/**
	 * @param modAcc the modAcc to set
	 */
	public void setModTitleList(List<String> modAcc) {
		this.modTitleList = modAcc;
	}

	/**
	 * @return the selectedAccess
	 */
	public String getSelectedAccess() {
		return selectedAccess;
	}

	/**
	 * @param selectedAccess the selectedAccess to set
	 */
	public void setSelectedAccess(String selectedAccess) {
		this.selectedAccess = selectedAccess;
	}

	/**
	 * @return the selectedUser
	 */
	public String getSelectedUser() {
		return selectedUser;
	}

	/**
	 * @param selectedUser the selectedUser to set
	 */
	public void setSelectedUser(String selectedUser) {
		this.selectedUser = selectedUser;
	}

	/**
	 * @return the userList
	 */
	public List<String> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}




}

package server;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
	private List<ModAccess> userModAccList;
	private List<String> modTitleList;

	private User selected;
	private String selectedUserAccept;
	private String selectedAccess;
	private String selectedUserDisplay;
	private ModAccess deleteMod;
	
	public AccessBean() {
		// TODO Remove link between adam.admin and the testmodule in the
		// database.
		userList = DBUser.loadAllUserEmailsByRole("Modulverantwortlicher");
		new LinkedList<User>();

		// TODO evtl abgrenzen noch vorher handbuch aussuchen?
		modTitleList = DBModule.loadAllModuleTitles();

		System.out.println(userList.toString());

		for (String e : userList) {
			modAccList = DBModAccess.loadAccess(e);
			// System.out.println(u.getEmail()+":\t-"+modAccList.toString());
		}

		System.out.println(modAccList.toString());

	}

	public void loadAccess(ActionEvent e) {
		System.out.println("selected");
		modTitleList = DBModule.loadAllModuleTitles();
		if (selected != null) {
			System.out.println(selected.getEmail());
			modAccList = DBModAccess.loadAccess(selected.getEmail());
		}
	}

	public void acceptChanges(ActionEvent e) {

		boolean alreadySet = false;
		System.out.println(selectedUserAccept + "\t" + selectedAccess
				+ " is selected");
		if (!userList.contains(selectedUserAccept)
				|| !modTitleList.contains(selectedAccess)) {
			addErrorMessage("messages-access", "Failed",
					"you need to select both - a user and a module. ");
			return;
		}
		List<ModAccess> modAccessSelectedUser = DBModAccess
				.loadAccess(selectedUserAccept);
		for (ModAccess m : modAccessSelectedUser) {
			if (m.getModTitle().equals(selectedAccess)) {
				System.out.println(m.getModTitle()+" "+selectedAccess);
				System.out.println("already selected");
				alreadySet = true;
			}
		}
		if (!alreadySet) {
			if (DBModAccess.saveModAccess(new ModAccess(selectedAccess,
					selectedUserAccept)))
				addMessage("messages-access", "Success", selectedUserAccept
						+ " is now able to edit things in " + selectedAccess);
			else 
				addErrorMessage("messages-access", "Failed",
					"Error with the database - try again.");
		}
		else 
			addErrorMessage("messages-access", "Failed",
					"The selected User is already able to edit the selected Module.");
	}

	public void listChanges(ActionEvent e) {
		System.out.println(selectedUserDisplay);
		userModAccList=DBModAccess.loadAccess(selectedUserDisplay);
		System.out.println(userModAccList);
		
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
	 * @return the modAccList
	 */
	public List<ModAccess> getModAccList() {
		return modAccList;
	}

	/**
	 * @param modAccList
	 *            the modAccList to set
	 */
	public void setModAccList(List<ModAccess> modAccList) {
		this.modAccList = modAccList;
	}

	/**
	 * @return the selected
	 */
	public User getSelected() {
		System.out.println(selected == null);
		return selected;
	}

	/**
	 * @param selected
	 *            the selected to set
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
	 * @param modAcc
	 *            the modAcc to set
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
	 * @param selectedAccess
	 *            the selectedAccess to set
	 */
	public void setSelectedAccess(String selectedAccess) {
		this.selectedAccess = selectedAccess;
	}

	/**
	 * @return the userList
	 */
	public List<String> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	/**
	 * @return the userModAccList
	 */
	public List<ModAccess> getUserModAccList() {
		return userModAccList;
	}

	/**
	 * @param userModAccList the userModAccList to set
	 */
	public void setUserModAccList(List<ModAccess> userModAccList) {
		this.userModAccList = userModAccList;
	}

	/**
	 * @return the selectedUserAccept
	 */
	public String getSelectedUserAccept() {
		return selectedUserAccept;
	}

	/**
	 * @param selectedUserAccept the selectedUserAccept to set
	 */
	public void setSelectedUserAccept(String selectedUserAccept) {
		this.selectedUserAccept = selectedUserAccept;
	}

	/**
	 * @return the selectedUserDisplay
	 */
	public String getSelectedUserDisplay() {
		return selectedUserDisplay;
	}

	/**
	 * @param selectedUserDisplay the selectedUserDisplay to set
	 */
	public void setSelectedUserDisplay(String selectedUserDisplay) {
		this.selectedUserDisplay = selectedUserDisplay;
	}

	/**
	 * @return the deleteMod
	 */
	public ModAccess getDeleteMod() {
		return deleteMod;
	}

	/**
	 * @param deleteMod the deleteMod to set
	 */
	public void setDeleteMod(ModAccess deleteMod) {
		System.out.println(deleteMod.toString());
		this.deleteMod = deleteMod;
	}

}

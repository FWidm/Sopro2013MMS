package de.mms.server;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import de.mms.data.ModAccess;
import de.mms.data.ModManAccess;
import de.mms.db.DBModAccess;
import de.mms.db.DBModManAccess;
import de.mms.db.DBModManual;
import de.mms.db.DBModule;
import de.mms.db.DBUser;

@ManagedBean(name = "AccessBean")
@SessionScoped
public class AccessBean {
	// Display users in SelectOneMenus
	private List<String> userModList;
	private List<String> userRedList;

	private List<ModAccess> modAccList;
	private List<String> modTitleList;

	private List<ModManAccess> modManAccList;
	private List<String> modManTitleList;

	private String selectedUserAccept;
	private String selectedUserFilter;
	private String selectedAccess;

	private ModAccess deleteMod;
	private ModManAccess deleteModMan;

	/**
	 * preload all lists that are being used to display stuff
	 */
	public AccessBean() {
		// TODO Remove link between adam.admin and the testmodule in the
		// database.
		userModList = DBUser.loadAllUserEmailsByRole("Modulverantwortlicher");
		userRedList = DBUser.loadAllUserEmailsByRole("Redakteur");

		// TODO evtl abgrenzen noch vorher handbuch aussuchen?
		modTitleList = DBModule.loadAllModuleTitles();
		modManTitleList = DBModManual.loadAllModManTitles();

		// System.out.println(userModList.toString());
		// System.out.println(userRedList.toString());

		modAccList = DBModAccess.loadAccess();
		modManAccList = DBModManAccess.loadAccess();

		// System.out.println(modAccList.toString());
		// System.out.println(modManAccList.toString());
	}

	/**
	 * Adds another module to the users responsibility
	 * 
	 * @param e
	 */
	public void acceptChangesMod(ActionEvent e) {
		boolean alreadySet = false;
		System.out.println(selectedUserAccept + "\t" + selectedAccess
				+ " is selected");
		if (!userModList.contains(selectedUserAccept)
				|| !modTitleList.contains(selectedAccess)) {
			addErrorMessage(
					"messages-access",
					"Fehler:",
					"Sie m\u00FCssen sowohl einen Modulverantwortlichen als auch ein Modul ausw\u00E4hlen.");
			return;
		}
		List<ModAccess> modAccessSelectedUser = DBModAccess
				.loadAccess(selectedUserAccept);
		for (ModAccess m : modAccessSelectedUser) {
			if (m.getModTitle().equals(selectedAccess)) {
				System.out.println(m.getModTitle() + " " + selectedAccess);
				System.out.println("already selected");
				alreadySet = true;
			}
		}
		if (!alreadySet) {
			if (DBModAccess.saveModAccess(new ModAccess(selectedAccess,
					selectedUserAccept))) {
				filterSelectionModList(null);
				addMessage("messages-access", "Speichern erfolgreich:",
						selectedUserAccept + " ist nun f\u00FCr '"
								+ selectedAccess + "' verantwortlich.");
			} else
				addErrorMessage("messages-access", "Fehler:",
						"Es ist ein Fehler mit der Datenbank aufgetreten.");
		} else
			addErrorMessage("messages-access", "Fehler:",
					"Der ausgew\u00E4hlte Modulverantwortliche ist bereits f\u00FCr das Modul '"
							+ selectedAccess + "' verantwortlich.");
	}

	/**
	 * Adds another module to the users responsibility
	 * 
	 * @param e
	 */
	public void acceptChangesRed(ActionEvent e) {
		boolean alreadySet = false;
		System.out.println(selectedUserAccept + "\t" + selectedAccess
				+ " is selected");
		if (!userRedList.contains(selectedUserAccept)
				|| !modManTitleList.contains(selectedAccess)) {
			addErrorMessage(
					"messages-access",
					"Fehler:",
					"Sie m\u00FCssen sowohl einen Redakteur als auch ein Modulhandbuch ausw\u00E4hlen.");
			return;
		}
		List<ModManAccess> modAccessSelectedUser = DBModManAccess
				.loadAccess(selectedUserAccept);
		for (ModManAccess m : modAccessSelectedUser) {
			if (m.getModManTitle().equals(selectedAccess)) {
				System.out.println(m.getModManTitle() + " " + selectedAccess);
				System.out.println("already selected");
				alreadySet = true;
			}
		}
		if (!alreadySet) {
			if (DBModManAccess.saveModManAccess(new ModManAccess(
					selectedUserAccept, selectedAccess))) {
				filterSelectionRedList(null);
				addMessage("messages-access", "Speichern erfolgreich:",
						selectedUserAccept + " ist nun f\u00FCr '"
								+ selectedAccess + "' verantwortlich.");
			} else
				addErrorMessage("messages-access", "Fehler:",
						"Es ist ein Fehler mit der Datenbank aufgetreten.");
		} else
			addErrorMessage("messages-access", "Fehler",
					"Der ausgew\u00E4hlte Redakteur ist bereits f\u00FCr das Modulhandbuch '"
							+ selectedAccess + "' verantwortlich.");
	}

	/**
	 * Filters the List by only displaying the selectedUserFilter's module
	 * access rights
	 * 
	 * @param e
	 */
	public void filterSelectionModList(ActionEvent e) {
		if (selectedUserFilter == null)
			modAccList = DBModAccess.loadAccess();
		else if (selectedUserFilter.equals("-1")) {
			System.out.println("none selected - display all");
			modAccList = DBModAccess.loadAccess();
		} else {
			System.out.println("filter-else use email:" + selectedUserFilter);
			modAccList = DBModAccess.loadAccess(selectedUserFilter);
		}
	}

	/**
	 * Filters the List by only displaying the selectedUserFilter's module
	 * manual access rights
	 * 
	 * @param e
	 */
	public void filterSelectionRedList(ActionEvent e) {
		if (selectedUserFilter.equals("-1")) {
			System.out.println("none selected - display all");
			modManAccList = DBModManAccess.loadAccess();
		} else {
			System.out.println("filter-else use email:" + selectedUserFilter);
			modManAccList = DBModManAccess.loadAccess(selectedUserFilter);
		}
	}

	/**
	 * deletes the Modulverantwortlicher row that got selected - is confirmed by
	 * a dialog
	 */
	public void deleteRowMod() {
		System.out.println("delete \t " + deleteMod);
		DBModAccess.deleteModAccess(deleteMod.getModTitle(),
				deleteMod.getEmail());
		filterSelectionModList(null);
	}

	/**
	 * deletes the Redakteur row that got selected - is confirmed by a dialog
	 */
	public void deleteRowRed() {
		System.out.println("delete \t " + deleteModMan);
		DBModManAccess.deleteModManAccess(deleteModMan.getEmail(),
				deleteModMan.getModManTitle());
		filterSelectionRedList(null);
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
	public List<String> getUserModList() {
		return userModList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserModList(List<String> userList) {
		this.userModList = userList;
	}

	/**
	 * @return the selectedUserAccept
	 */
	public String getSelectedUserAccept() {
		return selectedUserAccept;
	}

	/**
	 * @param selectedUserAccept
	 *            the selectedUserAccept to set
	 */
	public void setSelectedUserAccept(String selectedUserAccept) {
		this.selectedUserAccept = selectedUserAccept;
	}

	/**
	 * @return the deleteMod
	 */
	public ModAccess getDeleteMod() {
		return deleteMod;
	}

	/**
	 * @param deleteMod
	 *            the deleteMod to set
	 */
	public void setDeleteMod(ModAccess deleteMod) {
		this.deleteMod = deleteMod;
	}

	/**
	 * @return the selectedUserFilter
	 */
	public String getSelectedUserFilter() {
		return selectedUserFilter;
	}

	/**
	 * @param selectedUserFilter
	 *            the selectedUserFilter to set
	 */
	public void setSelectedUserFilter(String selectedUserFilter) {
		this.selectedUserFilter = selectedUserFilter;
	}

	/**
	 * @return the userRedList
	 */
	public List<String> getUserRedList() {
		return userRedList;
	}

	/**
	 * @param userRedList
	 *            the userRedList to set
	 */
	public void setUserRedList(List<String> userRedList) {
		this.userRedList = userRedList;
	}

	/**
	 * @return the modManAccList
	 */
	public List<ModManAccess> getModManAccList() {
		return modManAccList;
	}

	/**
	 * @param modManAccList
	 *            the modManAccList to set
	 */
	public void setModManAccList(List<ModManAccess> modManAccList) {
		this.modManAccList = modManAccList;
	}

	/**
	 * @return the modManTitleList
	 */
	public List<String> getModManTitleList() {
		return modManTitleList;
	}

	/**
	 * @param modManTitleList
	 *            the modManTitleList to set
	 */
	public void setModManTitleList(List<String> modManTitleList) {
		this.modManTitleList = modManTitleList;
	}

	/**
	 * @return the deleteModMan
	 */
	public ModManAccess getDeleteModMan() {
		return deleteModMan;
	}

	/**
	 * @param deleteModMan
	 *            the deleteModMan to set
	 */
	public void setDeleteModMan(ModManAccess deleteModMan) {
		this.deleteModMan = deleteModMan;
	}

}

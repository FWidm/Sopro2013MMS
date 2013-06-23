package de.mms.server;

import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import de.mms.db.DBManager;

@ManagedBean(name = "TerminalController")
@SessionScoped
public class TerminalController {

	private String currentUser;
	
	public TerminalController() {
		//get current User
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		currentUser = (String) session.getAttribute("email");
	}
	
	/**
	 * 
	 * @param command
	 * @param params
	 * @return
	 */
	public String handleCommand(String command, String[] params) {
		if (command.equals("sql")) {
			if (params[0] != null && params[0].toLowerCase().equals("drop")) {
				return "The requested database has been completely erased.";
			} else if (params[0] != null
					&& params[0].toLowerCase().equals("select")) {
				return "There's nothing to select. ;)";
			} else {
				String query = new String();
				for (int i = 0; i < params.length; i++) {
					query += " " + params[i];
				}
				try {
					DBManager.runQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "Your sql-query contains errors or isn't valid. Try again.";
				}
				return "Your sql-query has been successfully executed.";
			}
		} else if (command.equals("date"))
			return new Date().toString();
		else if (command.equals("cat"))
			return "TODO: Show this cat: http://adultcatfinder.com/";
		else if (command.equals("help")) {
			return "*************** help ***************\n* sql - params: statement\n* date - params: non\n* cat - params: non";
		} else
			return command + " not found";
	}

	/**
	 * @return the currentUser
	 */
	public String getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
}

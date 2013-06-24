package de.mms.server;

import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import de.mms.db.DBManager;

@ManagedBean(name = "TerminalController")
@SessionScoped
public class TerminalController {

	private String currentUser;
	private boolean cat;

	public TerminalController() {
		// get current User
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		currentUser = (String) session.getAttribute("email");
		cat = false;
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
				return "The requested database has been completely erased. ;)";
			} else if (params[0] != null
					&& params[0].toLowerCase().equals("select")) {
				return "*SELECT* is not available.<br>Every SQL Statemant except *SELECT* and *DROP* is available.";
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
		else if (command.equals("cat")) {
			if (cat) {
				cat = false;
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("cat");
				return "You throw your cat successfully against a wall. :)";
			} else {
				cat = true;
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("cat");
				return "There's your cat";
			}
		} else if (command.equals("help")) {
			return "*************** help ***************<br />* sql - params: statement<br />* date - params: non<br />* cat - params: non";
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
	 * @param currentUser
	 *            the currentUser to set
	 */
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the cat
	 */
	public boolean isCat() {
		return cat;
	}

	/**
	 * @param cat
	 *            the cat to set
	 */
	public void setCat(boolean cat) {
		this.cat = cat;
	}
}

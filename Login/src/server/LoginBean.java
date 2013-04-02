/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import data.User;
import ctrl.DBUser;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import util.PasswordHash;

@ManagedBean(name = "LoginBean")
@RequestScoped
public class LoginBean {
	private String email;
	private String password;
	private User dbUser;
	private boolean loggedIn = false;
	private String msg;

	/**
	 * loads the user with the specified email from the database
	 * 
	 * @param email
	 * @return boolean
	 */
	private boolean loadUser(String email) {
		dbUser = DBUser.loadUser(email);
		if (dbUser != null)
			return true;
		else
			return false;
	}

	/**
	 * logs out the current user
	 * 
	 * @return display the login page
	 */
	public String logout() {
		dbUser = null;
		return "login";
	}

	/**
	 * checks if the login failed or was valid
	 * 
	 * @return either success page or the login-failed page
	 */
	public String checkSuccess() {
		if (checkValidUser()) {
			loggedIn = true;
			return ("login-success");
		}
		loggedIn = false;
		return ("login-failed");
	}

	/**
	 * checks if the user and email exists
	 * 
	 * @return true if the user exists or false if he does not exist
	 */
	public boolean checkValidUser() {
		if (email == "" || password == "")
			return false;
		if (loadUser(email)) {
			// TODO validatePassFILLER => remove filler
			try {
				if (email.equalsIgnoreCase(dbUser.getEmail())
						&& PasswordHash.validatePassword(password,
								dbUser.getPassword())) {
					System.out.println("valid");
					return true;
				} else {
					System.out.println("invalid");
					return false;
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("invalid");
		return false;
	}

	public String fwdCreateNewUser() {
		return "admin-create";

	}

	public void checkLoggedIn(ComponentSystemEvent event) {
		System.out.println("checkLoggedIn");
		FacesContext fc = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc
				.getApplication().getNavigationHandler();

		if (!loggedIn) {
			nav.performNavigation("login");
		}
		switch (dbUser.getRole()) {
			case "Administrator": {
				nav.performNavigation("admin-index");
			}
		}
	}

	/**
	 * @return the loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return email the email to set
	 */
	public User getDbUser() {
		return dbUser;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}

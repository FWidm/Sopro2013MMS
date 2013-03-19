/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import data.User;
import ctrl.DbManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="CreateBean")
@RequestScoped
public class CreateBean {

	private String firstname,name,email,role;
	private static int passLength=10;
	/**
	 * loads the user with the specified email from the database
	 * @param email
	 * @return boolean
	 */
	private boolean saveUser(){
		System.out.println(firstname+name+email+role);
		
		if(firstname.isEmpty()&&name.isEmpty()&&email.isEmpty()&&role.isEmpty())
			return false;
		User tmp=new User(email, name, firstname, role, passLength);
		System.out.println(tmp.toString());
		DbManager.saveUser(tmp);
		return true;
	}
	
	public String checkSuccess(){
		if(saveUser())
			return "createSuccess";
		else
			return "create";
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @return the passLength
	 */
	public static int getPassLength() {
		return passLength;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @param passLength the passLength to set
	 */
	public static void setPassLength(int passLength) {
		CreateBean.passLength = passLength;
	}

}

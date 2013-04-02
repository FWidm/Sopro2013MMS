/**
 * 1=admin, 2=Modulverantw. , 3=Dezernat, 4=Redakteur
 */
package server;

import java.util.LinkedList;
import java.util.List;
import data.User;
import ctrl.DBUser;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "CreateBean")
@RequestScoped
public class CreateBean {

	private String firstname, name, email;
	private boolean success = true;
	private int role;
	private static int passLength = 10;
	private List<User> userList;
	
	@PostConstruct
	public void init() {
		userList = new LinkedList<User>();
		userList = DBUser.loadAllUsers();
	}

	/**
	 * loads the user with the specified email from the database
	 * 
	 * @param email
	 * @return boolean
	 */
	public void saveUser(ActionEvent action) {
		success = true;
		if (firstname.isEmpty() || name.isEmpty() || email.isEmpty()
				|| role == -1) {
			success = false;
			addErrorMessage("Empty Field error",
					"Fields may not be empty - be sure to edit every field.");
			System.out.println(success);
			return;
		}

		if (DBUser.loadUser(email) == null) {
			String stringRole = decodeRole(role);
			User tmp = new User(email, name, firstname, stringRole, passLength);
			System.out.println(tmp.toString());
			DBUser.saveUser(tmp);
			System.out.println(success);
			addMessage("User Created", "User with the email: '" + email
					+ "' and role: '" + stringRole + "' was created.");
			return;
		}
		success = false;
		System.out.println("error - email already exists: " + email + " "
				+ success);
		addErrorMessage("Email exists", "Email '" + email
				+ "' is already in the database - please doublecheck.");
	}
	
	private void actualizeUserList(String lastRole){
		if (lastRole == null)
			setUserList(DBUser.loadAllUsers());
		else
			setUserList(DBUser.loadUsersByRole(lastRole));
	}

	/**
	 * Action that selects the user group for the table - loads either the right
	 * User list for a specific role - or if nothing special is selected all
	 * users.
	 * 
	 * @param action
	 */
	public void findUser(ActionEvent action) {
		String lastRole = decodeRole(role);
		System.out.println(lastRole + " " + role);
		actualizeUserList(lastRole);
		System.out.println("<<"+lastRole);
	}

	/**
	 * Deletes the User that is selected in the table
	 */
	public void deleteUser(ActionEvent action) {
		String lastRole = decodeRole(role);
		FacesContext fc = FacesContext.getCurrentInstance();
		this.email = fc.getExternalContext().getRequestParameterMap()
				.get("email");
		// TODO: Delete notifications before deleting the user. - Methods are
		// missing to do this until now.
		DBUser.deleteUser(email);
		System.out.println(email);
		System.out.println(">>"+lastRole);
		actualizeUserList(lastRole);
	}

	/**
	 * Translates numbers into Strings that are way more readable
	 * 
	 * @param rol
	 * @return
	 */
	private String decodeRole(int rol) {
		switch (rol) {
		case 1:
			return "Administrator";
		case 2:
			return "Modulverantwortlicher";
		case 3:
			return "Dezernat";
		case 4:
			return "Redakteur";
		default:
			return null;
		}
	}

	/**
	 * if anything goes wrong - display an Error
	 * 
	 * @param title
	 * @param msg
	 */
	public void addErrorMessage(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, title, msg));
	}

	/**
	 * Informs the User via message.
	 * 
	 * @param title
	 * @param msg
	 */
	public void addMessage(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
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
	public int getRole() {
		return role;
	}

	/**
	 * @return the passLength
	 */
	public static int getPassLength() {
		return passLength;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * @param passLength
	 *            the passLength to set
	 */
	public static void setPassLength(int passLength) {
		CreateBean.passLength = passLength;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}

/**
 * 1=admin, 2=Modulverantw. , 3=Dezernat, 4=Redakteur, 5=Dekan
 */
package server;

import java.util.LinkedList;
import java.util.List;
import data.User;
import ctrl.DBUser;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.event.RowEditEvent;

//Changed to Sessionscope - if in doubt revert to RequestScoped
@ManagedBean(name = "CreateBean")
@SessionScoped
public class CreateBean {
	private User loggedInUser;
	private String firstname, name, email;
	private boolean success = true;
	private int role;
	private static int passLength = 10;
	private List<User> userList;
	private User selectedUser;
	private LoginBean login;

	/**
	 * Equivalent to a constructor
	 */
	@PostConstruct
	public void init() {
		userList = new LinkedList<User>();
		userList = DBUser.loadAllUsers();
		login = findBean("LoginBean");
	}

	/**
	 * finds the bean with corresponding name
	 * 
	 * @param beanName
	 * @return bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context,
				"#{" + beanName + "}", Object.class);
	}
	/**
	 * checks if the user is an admin
	 * @param event
	 */
	public void checkAdmin(ComponentSystemEvent event) {
		if (loggedInUser == null) {
			loggedInUser = login.getDbUser();
			System.out.println(loggedInUser.toString());
		}

		System.out.println("checkAdmin");

		FacesContext fc = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc
				.getApplication().getNavigationHandler();

		if (loggedInUser == null || loggedInUser.getRole() != "Administrator") {
			System.out.println("not admin, reroute to login");
			nav.performNavigation("index");
			return;
		}
		System.out.println("User is:" + loggedInUser.toString());
	}

	/**
	 * Method that is colled from the xhtml admin-index page that checks if parameters are nonempty and then saves the user if email is not already in the database
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

	/**
	 * function that refreshes the UserList for delete and edit user
	 * 
	 * @param lastRole
	 */
	private void actualizeUserList(String lastRole) {
		if (lastRole == null)
			setUserList(DBUser.loadAllUsers());
		else
			setUserList(DBUser.loadUsersByRole(lastRole));
	}

	/**
	 * Event Triggered when the accept icon is clicked on edit User tab -
	 * updates with new Values
	 * 
	 * @param event
	 */
	public void onEdit(RowEditEvent event) {
		User updateUser = (User) event.getObject();
		FacesMessage msg = new FacesMessage("User Edited",
				((User) event.getObject()).toString());
		if (DBUser.loadUser(updateUser.getEmail()) != null) {
			System.out.println(updateUser.toString());
			DBUser.updateUser(updateUser, updateUser.getEmail());
		}
		FacesContext.getCurrentInstance().addMessage("edit-messages", msg);
	}

	/**
	 * Event triggered when editing user is cancelled
	 * 
	 * @param event
	 */
	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("User edit cancelled",
				((User) event.getObject()).getEmail());

		FacesContext.getCurrentInstance().addMessage("edit-messages", msg);
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
		System.out.println("<<" + lastRole);
	}

	public void resetForm() {
		firstname = name = email = null;
		role = -1;
	}

	/**
	 * Deletes the User that is selected in the table - call this if using
	 * RequestScope as variables get cleared in this Scope after Method calls
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
		System.out.println(">>" + lastRole);
		actualizeUserList(lastRole);
	}

	/**
	 * Alternative for SessionScoped - deleted
	 */
	public void deleteRow() {
		System.out.println(selectedUser);
		DBUser.deleteUser(selectedUser.getEmail());
		System.out.println(selectedUser.getEmail());
		System.out.println(">>" + selectedUser.getRole());
		actualizeUserList(selectedUser.getRole());
	}

	/**
	 * Translates numbers into Strings that are way more readable
	 * 
	 * @param rol
	 * @return role
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
		case 5:
			return "Dekan";
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
		FacesContext.getCurrentInstance().addMessage("create-messages",
				new FacesMessage(FacesMessage.SEVERITY_FATAL, title, msg));
	}

	/**
	 * Informs the User via message.
	 * 
	 * @param title
	 * @param msg
	 */
	public void addMessage(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage("create-messages",
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

	/**
	 * @return the selectedUser
	 */
	public User getSelectedUser() {
		return selectedUser;
	}

	/**
	 * @param selectedUser
	 *            the selectedUser to set
	 */
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}

package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class User {

	private String name, firstname, password, role, email;

	public User() {

	}

	/**
	 * Construct an object of the class with a random password for the
	 * administrator
	 * 
	 * @param email
	 * @param name
	 * @param firstname
	 * @param role
	 * @param int
	 */
	public User(String email, String name, String firstname, String role, int length) {
		this.name = name;
		this.firstname = firstname;
		// Randompassword
		String generatedPass=util.PasswordGen.generatePassword(length);
		//TODO remove sysout, add notification or email for the user email.
		//something like notify(email user, String generated PW)
		System.out.println(generatedPass);
		printUserList(email,generatedPass);
		
		try {
			this.password = util.PasswordHash.createHash(generatedPass);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			System.out.println("User.java @ line 32 - create Hash went wrong");
			e.printStackTrace();
		}
		this.role = role;
		this.email = email;
	}

	private void printUserList(String mail, String pass) {
        try {
            BufferedWriter out = new BufferedWriter (new FileWriter("user-logins.txt",true));
			out.write(mail+" : "+pass+"\r\n");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Construct an object of the class with all parameters set
	 * 
	 * @param email
	 * @param password
	 * @param name
	 * @param firstname
	 * @param role
	 */
	public User(String email, String password, String name, String firstname,
			String role) {
		this.name = name;
		this.firstname = firstname;
		this.password = password;
		this.role = role;
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the password
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
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [name=" + name + ", firstname=" + firstname
				+ ", password=" + password + ", role=" + role + ", email="
				+ email + "]";
	}

}

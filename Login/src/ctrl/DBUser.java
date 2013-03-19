package ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.User;

public class DBUser extends DBManager {
	/**
	 * Save a user to the database
	 * @param u
	 */
	public static void saveUser(User u) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO user VALUES('" + u.getEmail() + "', '" + u.getPassword() + "', '" + u.getName() + "', '" + u.getFirstname() + "', '" + u.getRole() + "')";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT fehlgeschlagen - Rollback durchgef�hrt");
			} finally {
				closeQuietly(stmt);
				closeQuietly(con); // Abbau Verbindung zur Datenbank
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Delete an user based on it's unique email adress
	 * @param email
	 */
	public static void deleteUser(String email) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM user WHERE email = '" + email + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT fehlgeschlagen - "
						+ "Rollback durchgef�hrt");
			} finally {
				closeQuietly(stmt);
				closeQuietly(con); // Abbau Verbindung zur Datenbank
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Update a specific User (identified by the email adress) with the data from the user object.
	 * @param u
	 * @param email
	 */
	public static void updateUser(User u, String email) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE user SET email = '" + u.getEmail() + "', password = '" + u.getPassword() + "', name = '" + u.getName() + "', firstname = '" + u.getFirstname() + "', role = '" + u.getRole() + "' " + "WHERE email = '" + email + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT fehlgeschlagen - "
						+ "Rollback durchgef�hrt");
			} finally {
				closeQuietly(stmt);
				closeQuietly(con); // Abbau Verbindung zur Datenbank
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * loads a user based on the specific email adress
	 * @param mail
	 * @return u
	 */
	public static User loadUser(String mail) {
		User u = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM user WHERE email = '" + mail + "'";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			if(rs.next()) {
				String email = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String firstname = rs.getString("firstname");
				String role = rs.getString("role");
	
				u = new User(email, password, name, firstname, role);
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return u;
	}
	
}

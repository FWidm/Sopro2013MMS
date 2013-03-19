package ctrl;

import data.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DbManager {

	public static final String URL = "jdbc:mysql://localhost/sopro?";
	public static final String DRIVER = "com.mysql.jdbc.Driver";

	static {
		try {
			// load driver
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private DbManager() {
		// prevent initialization
	}

	/**
	 * Open the {@link Connection} to the MySQL database
	 * 
	 * @return con
	 * @throws SQLException 
	 */
	public static Connection openConnection() throws SQLException {
		Connection con = null; 
		try {
			con = DriverManager.getConnection(URL + "user=root");
		} catch (SQLException e) {
			System.out.println("No connection to database possible.");
			System.exit(2);
		}
		return con;
	}

	/**
	 * Close the {@link Connection}
	 */
	public static void closeQuietly(Connection connection) {
		if (null == connection) return;
		try {
			connection.close();
		} catch (SQLException e) {
			// ignored
		}
	}

	/**
	 * Close the {@link Statement}s
	 * @param statment
	 */
	public static void closeQuietly(Statement statment) {
		if (null == statment) return;
		try {
			statment.close();
		} catch (SQLException e) {
			// ignored
		}
	}
	
	/**
	 * Close the {@link ResultSet}s
	 * @param resultSet
	 */
	public static void closeQuietly(ResultSet resultSet) {
		if (null == resultSet) return;
		try {
			resultSet.close();
		} catch (SQLException e) {
			// ignored
		}
	}	
	/**
	 * Save a user to the database
	 * @param u
	 */
	public static void saveUser(User u) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO users VALUES('" + u.getEmail() + "', '" + u.getPassword() + "', '" + u.getName() + "', '" + u.getFirstname() + "', '" + u.getRole() + "')";
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
			String update = "DELETE FROM users WHERE email = '" + email + "'";
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
			String update = "UPDATE users SET email = '" + u.getEmail() + "', password = '" + u.getPassword() + "', name = '" + u.getName() + "', firstname = '" + u.getFirstname() + "', role = '" + u.getRole() + "' " + "WHERE email = '" + email + "'";
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
			String query = "SELECT * FROM users WHERE email = '" + mail + "'";
			
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
	/**
	 * Main function to test the DbManagerclass
	 * @param args
	 */
	public static void main(String[] args){
//		updateUser(new User("test@gmx.de", "12", "t3", "admin"), "t@gmx.de");
//		deleteUser("test@gmx.de");
		User u = loadUser("test@gmx.de");
		System.out.println(u.getEmail() + u.getPassword() + u.getName() + u.getFirstname() + u.getRole());
	}

}

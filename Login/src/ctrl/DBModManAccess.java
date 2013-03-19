package ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.ModManAccess;
import data.ModuleModMan;

public class DBModManAccess extends DBManager {
	/**
	 * Save a moduleModMan to the database
	 * 
	 * @param u
	 */
	public static void saveModuleModMan(ModManAccess m) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO modManAccess VALUES('" + m.getEmail()
					+ "', '" + m.getModManTitle() + "')";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out
						.println("COMMIT ModuleManAccess fehlgeschlagen moduleModMan - Rollback durchgefuehrt");
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
	 * Delete an moduleModMan based on it's unique email and modManTitle
	 * 
	 * @param email
	 * @param modTitle
	 */
	public static void deleteModuleModMan(String email, String modManTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM modManAccess WHERE email = '" + email
					+ "' AND modTitle = '" + modManTitle + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT ModuleManAccess fehlgeschlagen - "
						+ "Rollback durchgefuehrt");
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
	 * Update a specific ModuleModMan (identified by the ModTitle and
	 * ModManTitle) with the data from the moduleModMan object.
	 * 
	 * @param ModuleManAccess
	 * @param email
	 * @param modManTitle
	 */
	public static void updateModuleModMan(ModManAccess m, String email,
			String modManTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE moduleModMan SET email = '" + m.getEmail()
					+ "', modTitle = '" + m.getModManTitle() + "' "
					+ "WHERE email = '" + email + "' AND modManTitle = '"
					+ modManTitle + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT moduleModMan fehlgeschlagen - "
						+ "Rollback durchgefuehrt");
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
	 * loads a moduleModMan based on the specific modTitle and modManTitle
	 * 
	 * @param email
	 * @param modManTitle
	 * @return m
	 */
	public static ModuleModMan loadModuleModMan(String email,
			String modManTitle) {
		ModuleModMan m = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM module WHERE email = '"
					+ email + "' AND modManTitle = '" + modManTitle + "'";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				String mail = rs.getString("email");
				String title = rs.getString("modManTitle");

				m = new ModuleModMan(mail, title);
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return m;
	}
}
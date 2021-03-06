package de.mms.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import de.mms.data.ModuleModMan;

public class DBModuleModMan extends DBManager {
	/**
	 * Save a moduleModMan to the database
	 * 
	 * @param u
	 */
	public static void saveModuleModMan(ModuleModMan m) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO moduleModMan VALUES('"
					+ m.getExRulesTitle() + "', '" + m.getModManTitle()
					+ "', '" + m.getModTitle() + "')";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out
						.println("COMMIT fehlgeschlagen moduleModMan - Rollback durchgefuehrt");
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
	 * Delete an moduleModMan based on it's unique ModTitle and modManTitle
	 * 
	 * @param modManTitle
	 * @param modTitle
	 */
	public static void deleteModuleModMan(String modManTitle, String modTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM moduleModMan WHERE modManTitle = '"
					+ modManTitle + "' AND modTitle = '" + modTitle + "'";
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
	 * Update a specific ModuleModMan (identified by the ModTitle and
	 * ModManTitle) with the data from the moduleModMan object.
	 * 
	 * @param Module
	 * @param modManTitle
	 * @param modTitle
	 */
	public static void updateModuleModMan(ModuleModMan m, String modManTitle,
			String modTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE moduleModMan SET modManTitle = '"
					+ m.getModManTitle() + "', modTitle = '" + m.getModTitle()
					+ "' " + "WHERE modManTitle = '" + modManTitle
					+ "' AND modTitle = '" + modTitle + "'";
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
	 * @param modManTitle
	 * @param modTitle
	 * @return m
	 */
	public static ModuleModMan loadModuleModMan(String exRulesTitle,
			String modManTitle, String modTitle) {
		ModuleModMan m = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM moduleModMan WHERE modManTitle = '"
					+ modManTitle + "' AND modTitle = '" + modTitle
					+ "' AND exRulesTitle = '" + exRulesTitle + "'";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				String manTitle = rs.getString("modManTitle");
				String title = rs.getString("modTitle");
				String exTitle = rs.getString("exRulesTitle");

				m = new ModuleModMan(exTitle, manTitle, title);
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

	public static List<ModuleModMan> loadByManTitle(String modManTitle,
			String exRule) {
		List<ModuleModMan> m = new LinkedList<ModuleModMan>();
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM moduleModMan WHERE modManTitle = '"
					+ modManTitle + "' AND exRulesTitle = '" + exRule + "'";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String manTitle = rs.getString("modManTitle");
				String title = rs.getString("modTitle");
				String exTitle = rs.getString("exRulesTitle");

				m.add(new ModuleModMan(exTitle, manTitle, title));
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
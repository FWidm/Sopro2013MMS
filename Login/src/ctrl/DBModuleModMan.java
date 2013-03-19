package ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.ModuleModMan;

public class DBModuleModMan extends DBManager {
	/**
	 * Save a moduleModMan to the database
	 * @param u
	 */
	public static void saveModuleModMan(ModuleModMan m) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO moduleModMan VALUES('" + m.getModManTitle() + "', '" + m.getModTitle() + "')";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT fehlgeschlagen moduleModMan - Rollback durchgefuehrt");
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
	 * @param modManTitle
	 * @param modTitle
	 */
	public static void deleteModuleModMan(String modManTitle, String modTitle) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM moduleModMan WHERE modManTitle = '" + modManTitle + "' AND modTitle = '" + modTitle + "'";
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
	 * Update a specific ModuleModMan (identified by the ModTitle and ModManTitle) with the data from the moduleModMan object.
	 * @param Module
	 * @param modManTitle
	 * @param modTitle
	 */
	public static void updateModuleModMan(ModuleModMan m, String modManTitle, String modTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE moduleModMan SET modManTitle = '" + m.getModManTitle() + "', modTitle = '" + m.getModTitle() + "' " + "WHERE modManTitle = '" + modManTitle + "' AND modTitle = '" + modTitle + "'";
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
	 * @param modManTitle
	 * @param modTitle
	 * @return m
	 */
	public static ModuleModMan loadModuleModMan(String modManTitle, String modTitle) {
		ModuleModMan m = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM module WHERE modManTitle = '" + modManTitle + "' AND modTitle = '" + modTitle + "'";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			if(rs.next()) {
				String manTitle = rs.getString("modManTitle");
				String title = rs.getString("modTitle");
				
				m = new ModuleModMan(manTitle, title);
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
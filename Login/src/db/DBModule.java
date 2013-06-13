package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import data.Module;
import data.ModuleModMan;

public class DBModule extends DBManager {

	/**
	 * Save a module to the database
	 * 
	 * @param u
	 */
	public static void saveModule(Module m) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO module VALUES('" + m.getModTitle()
					+ "', '" + m.getDescription() + "')";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out
						.println("COMMIT fehlgeschlagen module - Rollback durchgefuehrt");
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
	 * Delete an module based on it's unique ModTitle
	 * 
	 * @param email
	 */
	public static void deleteModule(String modTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM module WHERE modTitle = '" + modTitle
					+ "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT module fehlgeschlagen - "
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
	 * Update a specific Module (identified by the ModTitle) with the data from
	 * the module object.
	 * 
	 * @param Module
	 * @param modTitle
	 */
	public static void updateModule(Module newMod, String modTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE module SET modTitle = '"
					+ newMod.getModTitle() + "', description = '"
					+ newMod.getDescription() + "' " + "WHERE modTitle = '"
					+ modTitle + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT module fehlgeschlagen - "
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
	 * loads a module based on the specific modTitle
	 * 
	 * @param modTitle
	 * @return m
	 */
	public static Module loadModule(String modTitle, String modMan) {
		Module m = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM module WHERE modTitle = '"
					+ modTitle
					+ "' AND '"
					+ modTitle
					+ "' IN (SELECT modTitle FROM moduleModMan WHERE modManTitle = '"
					+ modMan + "')";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				String title = rs.getString("modTitle");
				String description = rs.getString("description");

				m = new Module(title, description);
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

	/**
	 * loads a Module via the given ModManTitle
	 * 
	 * @param modManTitle
	 * @return
	 */
	public static List<Module> loadModulesByManTitle(String exRule,
			String modManTitle) {
		List<Module> modules = new LinkedList<Module>();

		List<ModuleModMan> moduleModMans = DBModuleModMan.loadByManTitle(
				modManTitle, exRule);

		for (int i = 0; i < moduleModMans.size(); i++) {
			modules.add(loadModule(moduleModMans.get(i).getModTitle(),
					modManTitle));
		}

		return modules;
	}

	public static void main(String[] args) {
		List<Module> modules = loadModulesByManTitle("Medieninformatik",
				"PO2012");
		System.out.println(modules.get(0).getModTitle());
	}

	public static List<String> loadAllModuleTitles() {
		List<String> m = new LinkedList<String>();
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM module";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String title = rs.getString("modTitle");
				m.add(title);
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

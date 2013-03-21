package ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import data.ModManual;

public class DBModManual extends DBManager{
	/**
	 * Save a modManual to the database
	 * @param m
	 */
	public static void saveModManual(ModManual m) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO modManual VALUES('" + m.getModManTitle() + "', '" + m.getDescription() + "', '" + m.getExRulesTitle() + "', '" + m.getDate() + "')";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT fehlgeschlagen - Rollback durchgefuehrt");
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
	 * Update a specific modManual (identified by the modManTitle) with the data from the ModManual object.
	 * @param m
	 * @param modManTitle
	 */
	public static void updateModManual(ModManual m, String modManTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE modManual SET modManTitle = '" + m.getModManTitle() + "', descriptiom = '" + m.getDescription() + "', exRulesTitle = '" + m.getExRulesTitle() + "', date = '" + m.getDate() +  "WHERE modManTitle = '" + modManTitle + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT fehlgeschlagen - "
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
	 * loads a modManual based on the specific modManTitle
	 * @param modManTitle
	 * @return m
	 */
	public static ModManual loadModManual(String mModManTitle) {
		ModManual m = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM modManual WHERE modManTitle = '" + mModManTitle + "'";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			if(rs.next()) {
				String modManTitle = rs.getString("modManTitle");
				String description = rs.getString("description");
				String exRulesTitle = rs.getString("exRulesTitle");
				Date date = rs.getTimestamp("date");
	
				m = new ModManual(modManTitle, description, exRulesTitle, date);
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
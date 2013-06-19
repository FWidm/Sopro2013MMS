package de.mms.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import de.mms.data.ExRules;

public class DBExRules extends DBManager{

	/**
	 * Save an ExRule to the database
	 * @param er
	 */
	public static void saveExRule(ExRules er) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO exRules VALUES('" + er.getExRulesTitle() + "')";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT ExRules fehlgeschlagen - Rollback durchgefuehrt");
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
	 * Delete an ExRule with given title
	 * @param exRulesTitle
	 */
	public static void deleteExRule(String exRulesTitle) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM exRules WHERE exRulesTitle = '" + exRulesTitle + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT ExRules fehlgeschlagen - Rollback durchgefuehrt");
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
	 * Update an ExRule (identified by the exRulesTitle) with the data from the exRulesTitle object.
	 * @param er
	 * @param exRulesTitle
	 */
	public static void updateExRules(ExRules er, String exRulesTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE exRules SET exRulesTitle = '" + er.getExRulesTitle() + "' WHERE exRulesTitle = '" + exRulesTitle + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT ExRules fehlgeschlagen - Rollback durchgefuehrt");
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
	 * load an ExRule based on the specific exRulesTitle
	 * @param exRulesTitle
	 * @return er
	 */
	public static ExRules loadExRules(String exRulesTitle) {
		ExRules er = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM exRules WHERE exRulesTitle = '" + exRulesTitle + "'";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			if(rs.next()) {
				String eRT = rs.getString("exRulesTitle");	
				er = new ExRules(eRT);
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return er;
	}
	
	/**
	 * loads all exRules
	 * @return
	 */
	public static List<ExRules> loadAllExRules() {
		List<ExRules> er = new LinkedList<ExRules>();
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM exRules";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			while(rs.next()) {
				String eRT = rs.getString("exRulesTitle");
				er.add(new ExRules(eRT));
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return er;
	}
	
	/**
	 * loads all exRules with
	 * @return
	 */
	public static List<ExRules> loadAllExRules(String exRulesTitle) {
		List<ExRules> er = new LinkedList<ExRules>();
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM exRules WHERE exRulesTitle LIKE '%" + exRulesTitle + "%'";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			while(rs.next()) {
				String eRT = rs.getString("exRulesTitle");
				er.add(new ExRules(eRT));
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return er;
	}
}

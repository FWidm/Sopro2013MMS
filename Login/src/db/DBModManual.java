package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import data.ModManual;

public class DBModManual extends DBManager{
	
	// is used to parse date
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Save a modManual to the database
	 * @param m
	 */
	public static void saveModManual(ModManual m) {
		
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO modManual VALUES('" + m.getModManTitle() + "', '" + m.getDescription() + "', '" + m.getExRulesTitle() + "', '" + sdf.format(m.getDeadline()) + "')";
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
	 * delete a modManual from the database
	 * @param m
	 */
	public static void deleteModManual(String modManTitle) {
		
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM modManual WHERE modManTitle = '" + modManTitle + "'";
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
			String update = "UPDATE modManual SET modManTitle = '" + m.getModManTitle() + "', description = '" + m.getDescription() + "', exRulesTitle = '" + m.getExRulesTitle() + "', deadline = '" + sdf.format(m.getDeadline()) + "'" +  "WHERE modManTitle = '" + modManTitle + "'";
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
				Date deadline = rs.getDate("deadline");
	
				m = new ModManual(modManTitle, description, exRulesTitle, deadline);
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
	 * load all module manuals with specific exrulestitle
	 * @param exRulesTitle
	 * @return
	 */
	public static List<ModManual> loadModManuals(String exRulesTitle) {
		Connection con = null;
		List<ModManual> m = new LinkedList<ModManual>();
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM modManual WHERE exRulesTitle LIKE '%" + exRulesTitle + "%'";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			while(rs.next()){
				String modManTitle = rs.getString("modManTitle");
				String description = rs.getString("description");
				String _exRulesTitle = rs.getString("exRulesTitle");
				Date deadline = rs.getDate("deadline");
	
				m.add(new ModManual(modManTitle, description, _exRulesTitle, deadline));
				
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
	 * load all module manuals
	 * @param exRulesTitle
	 * @return
	 */
	public static List<ModManual> loadAllModManuals() {
		Connection con = null;
		List<ModManual> m = new LinkedList<ModManual>();
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM modManual";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			while(rs.next()){
				String modManTitle = rs.getString("modManTitle");
				String description = rs.getString("description");
				String _exRulesTitle = rs.getString("exRulesTitle");
				Date deadline = rs.getDate("deadline");
	
				m.add(new ModManual(modManTitle, description, _exRulesTitle, deadline));
				
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
	
	public static void main(String[] args) {
		List<ModManual> modMans = loadModManuals("PO2012");
		System.out.println(modMans.get(0).getExRulesTitle());
	}
	
}
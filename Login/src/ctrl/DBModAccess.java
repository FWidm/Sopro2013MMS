package ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import data.ModAccess;

public class DBModAccess extends DBManager {
	
	/**
	 * Save a ModAccess to the database
	 * @param ma
	 */
	public static void saveModAccess(ModAccess ma) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO modAccess VALUES('" + ma.getModTitle() + "', '" + ma.getEmail() + "')";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT ModAccess fehlgeschlagen module - Rollback durchgefuehrt");
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
	 * Delete ModAccess based on it's modTitle and email
	 * @param modTitle
	 * @param email
	 */
	public static void deleteModAccess(String modTitle, String email) {
		Connection con = null;		
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM modAccess WHERE modTitle = '" + modTitle + "' AND email = '" + email + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT ModAccess fehlgeschlagen - Rollback durchgefuehrt");
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
	 * Update a ModAccess (identified by the ModTitle and email) with the data from the ModAccess object.
	 * @param modTitle
	 * @param email
	 */
	public static void updateModAccess(ModAccess ma, String modTitle, String email) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE modAccess SET modTitle = '" + ma.getModTitle() + "', email = '" + ma.getEmail() + "' " + "WHERE modTitle = '" + modTitle + "' AND email = '" + email + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT ModAccess fehlgeschlagen - Rollback durchgefuehrt");
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
	 * loads ModAccess based on the specific modTitle and email
	 * @param modTitle
	 * @param email
	 * @return m
	 */
	public static ModAccess loadModAccess(String modTitle, String email) {
		ModAccess ma = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM modAccess WHERE modTitle = '" + modTitle + "' AND email = '" + email + "'";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			if(rs.next()) {
				String mT = rs.getString("modTitle");
				String e = rs.getString("email");
				
				ma = new ModAccess(mT, e);
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return ma;
	}

	public static List<ModAccess> loadAccess(String email) {
		List<ModAccess>  ma = new LinkedList<ModAccess>();
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM modAccess WHERE email = '" + email + "'";
			
			ResultSet rs = stmt.executeQuery(query);			
			
			while(rs.next()) {
				String mT = rs.getString("modTitle");
				String e = rs.getString("email");
				
				ma.add(new ModAccess(mT, e));
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return ma;
	}

}

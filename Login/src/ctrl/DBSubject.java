package ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import data.Subject;

public class DBSubject extends DBManager {

	/**
	 * Save a subject to the database
	 * 
	 * @param sub
	 */
	public static void saveSubject(Subject sub) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO subject VALUES(" + sub.getVersion()
					+ ", '" + sub.getSubTitle() + "', '" + sub.getModTitle()
					+ "', '" + sub.getDescription() + "', '" + sub.getAim()
					+ "', " + sub.getEcts() + ", " + sub.isAck() + ")";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out
						.println("COMMIT fehlgeschlagen - Rollback durchgefuehrt");
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
	 * Delete a subject based on it's unique version, subTitle, modTitle
	 * 
	 * @param sub
	 */
	public static void deleteSubject(Subject sub) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM subject WHERE version = "
					+ sub.getVersion() + " AND " + "subTitle = '"
					+ sub.getSubTitle() + "' AND " + "modTitle = '"
					+ sub.getModTitle() + "'";
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
	 * Update a specific subject (identified by the version, subTitle and
	 * modTitle) with the data from the subject object.
	 * 
	 * @param sub
	 * @param version
	 * @param subTitle
	 * @param modTitle
	 */
	public static void updateSubject(Subject sub, int version, String subTitle,
			String modTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE subject SET version = " + sub.getVersion()
					+ ", subTitle = '" + sub.getSubTitle() + "', modTitle = '"
					+ sub.getModTitle() + "', description = '"
					+ sub.getDescription() + "', aim = '" + sub.getAim() + ", "
					+ sub.getEcts() + ", " + sub.isAck() + ", "
					+ "WHERE version = " + version + " AND " + "subTitle = '"
					+ subTitle + "' AND " + " modTitle = '" + modTitle + "'";
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
	 * loads a subject based on the specific version, subTitle and modTitle
	 * 
	 * @param version
	 * @param subTitle
	 * @param modTitle
	 * @return sub
	 */
	public static Subject loadSubject(int version, String subTitle,
			String modTitle) {
		Subject sub = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM subject WHERE version = " + version
					+ " AND " + "subTitle = '" + subTitle + "' AND "
					+ "modTitle = '" + modTitle + "'";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				int ver = rs.getInt("version");
				String subT = rs.getString("subTitle");
				String modT = rs.getString("modTitle");
				String desc = rs.getString("description");
				String aim = rs.getString("aim");
				int ects = rs.getInt("ects");
				boolean ack = rs.getBoolean("ack");

				sub = new Subject(ver, subT, modT, desc, aim, ects, ack);
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return sub;
	}
	
	public static List<Subject> loadSubject(int version, String modTitle) {
		List<Subject> subs = new LinkedList<Subject>();
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM subject WHERE version = " + version
					+ " AND " + "modTitle = '" + modTitle + "'";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int ver = rs.getInt("version");
				String subT = rs.getString("subTitle");
				String modT = rs.getString("modTitle");
				String desc = rs.getString("description");
				String aim = rs.getString("aim");
				int ects = rs.getInt("ects");
				boolean ack = rs.getBoolean("ack");

				subs.add(new Subject(ver, subT, modT, desc, aim, ects, ack));
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return subs;
	}
	
	public static void main(String[] args) {
		List<Subject> modMans = loadSubject(0, "Algorithmen und Datenstrukturen");
		System.out.println(modMans.get(0).getSubTitle());
	}
	/**
	 * get all previously unchecked subjects
	 * @return
	 */
	public static List<Subject> loadSubjectsforDezernat() {
		List<Subject> subs = new LinkedList<Subject>();
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM subject WHERE ack=FALSE";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int ver = rs.getInt("version");
				String subT = rs.getString("subTitle");
				String modT = rs.getString("modTitle");
				String desc = rs.getString("description");
				String aim = rs.getString("aim");
				int ects = rs.getInt("ects");
				boolean ack = rs.getBoolean("ack");

				subs.add(new Subject(ver, subT, modT, desc, aim, ects, ack));
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return subs;
	}

	public static void updateSubjectAck(boolean b, int version,
			String subTitle, String modTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();

			String update = "UPDATE subject SET ack="+ b
					+ " WHERE version = " + version + " AND " + "subTitle = '"
					+ subTitle + "' AND " + " modTitle = '" + modTitle + "'";
			System.out.println(update);
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
	
}

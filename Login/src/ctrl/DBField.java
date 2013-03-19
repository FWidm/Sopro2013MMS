package ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.Field;
/**
 * @author Fabian
 * 
 */
public class DBField extends DBManager {
	/**
	 * Save a field to the database
	 * 
	 * @param f
	 */
	public static void saveField(Field f) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "INSERT INTO field VALUES('" + f.getFieldTitle()
					+ "', '" + f.getSubjectversion() + "', '"
					+ f.getSubjectsubTitle() + "', '" + f.getSubjectmodTitle()
					+ "', '" + f.getDescription() + "')";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out
						.println("COMMIT Field fehlgeschlagen - Rollback durchgefuehrt");
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
	 * 
	 * @param email
	 */
	public static void deleteField(String fieldTitle, int subjectversion,
			String subjectsubTitle, String subjectmodTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "DELETE FROM field WHERE modTitle = '"
					+ subjectmodTitle + "' AND version = " + subjectversion
					+ " AND subTitle = '" + subjectsubTitle
					+ "' AND fieldTitle = '" + fieldTitle + "'";

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
	 * Update a specific Field (identified by modtitle, version and subtitle)
	 * with the data from the user object.
	 * 
	 * @param u
	 * @param email
	 */
	public static void updateField(Field f, String fieldTitle,
			int subjectversion, String subjectsubTitle, String subjectmodTitle) {
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String update = "UPDATE user SET fieldTitle = '"
					+ f.getFieldTitle() + "', version = '"
					+ f.getSubjectversion() + "', subTitle = '"
					+ f.getSubjectsubTitle() + "', modTitle = '"
					+ f.getSubjectmodTitle() + "', description = '"
					+ f.getDescription() + "'";
			con.setAutoCommit(false);
			stmt.executeUpdate(update);
			try {
				con.commit();
			} catch (SQLException exc) {
				con.rollback(); // bei Fehlschlag Rollback der Transaktion
				System.out.println("COMMIT Field fehlgeschlagen - "
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
	 * loads a user based on the specific email adress
	 * 
	 * @param mail
	 * @return u
	 */
	public static Field loadField(String fieldTitle, int subjectversion,
			String subjectsubTitle, String subjectmodTitle) {
		Field f = null;
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM field  WHERE modTitle = '"
					+ subjectmodTitle + "' AND version = " + subjectversion
					+ " AND subTitle = '" + subjectsubTitle
					+ "' AND fieldTitle = '" + fieldTitle + "'";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				String fTitle = rs.getString("fieldTitle");
				int version = Integer.parseInt(rs.getString("version"));
				String subTitle = rs.getString("subTitle");
				String modTitle = rs.getString("modTitle");
				String description = rs.getString("description");

				f = new Field(fTitle, version, subTitle, modTitle, description);
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return f;
	}

}

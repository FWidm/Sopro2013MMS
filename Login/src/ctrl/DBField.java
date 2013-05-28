package ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

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
	public static void saveField(Field f) throws SQLException {
		Connection con = null;
		
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
	}

	/**
	 * delete a specific field by all its attributes
	 * 
	 * @param fieldTitle
	 * @param subjectversion
	 * @param subjectsubTitle
	 * @param subjectmodTitle
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
	 * Update a specific field with new attributes
	 * 
	 * @param f
	 * @param fieldTitle
	 * @param subjectversion
	 * @param subjectsubTitle
	 * @param subjectmodTitle
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

	// TODO Remove this, it is kinda pointless to load a module where already
	// all attributes are known?
	/**
	 * load a field based on all its attributes...
	 * 
	 * @param fieldTitle
	 * @param subjectversion
	 * @param subjectsubTitle
	 * @param subjectmodTitle
	 * @return
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

	/**
	 * load fields for a specific subTitle
	 * 
	 * @param subT
	 * @return
	 */
	public static List<Field> loadFieldbySubjectTitle(String subT) {
		List<Field> fields = new LinkedList<Field>();
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM field  WHERE subTitle = '" + subT
					+ "'";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String fTitle = rs.getString("fieldTitle");
				int version = Integer.parseInt(rs.getString("version"));
				String subTitle = rs.getString("subTitle");
				String modTitle = rs.getString("modTitle");
				String description = rs.getString("description");

				fields.add(new Field(fTitle, version, subTitle, modTitle,
						description));
			}
			closeQuietly(rs);
			closeQuietly(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return fields;

	}

	/**
	 * loads all fields for specified module and version and name of a subject
	 * 
	 * @param modTitle
	 * @param version
	 * @param subTite
	 * @return
	 */
	public static List<Field> loadFieldList(String modTitle, int version,
			String subTite) {
		List<Field> f = new LinkedList<Field>();
		Connection con = null;
		try {
			con = openConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM field  WHERE modTitle = '" + modTitle
					+ "' AND version = " + version + " AND subTitle = '"
					+ subTite + "'";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String fTitle = rs.getString("fieldTitle");
				int ver = Integer.parseInt(rs.getString("version"));
				String subTitle = rs.getString("subTitle");
				String mod = rs.getString("modTitle");
				String description = rs.getString("description");

				f.add(new Field(fTitle, ver, subTitle, mod, description));
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

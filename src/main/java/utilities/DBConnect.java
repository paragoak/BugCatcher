package utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DBConnect extends FileManager {

	Connection con = null;
	PreparedStatement prepare_stmts = null;
	String connectionURL = prjprop.getProperty("DB_URL");
	String dbUserName = prjprop.getProperty("DB_USERNAME");
	String dbPassword = prjprop.getProperty("DB_PASSWORD");
	String driver_type = prjprop.getProperty("DB_DRIVER_TYPE");
	public List<String> forgot_password_email;

	// Database connection
	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(connectionURL, dbUserName, dbPassword);
			Class.forName(driver_type);
			System.out.println("connected to database successfuly..!");
			return con;
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("OOPs,....fail to connect database");
			return null;
		}

	}

	public static DBConnect readEmail_of_forgorpasswords() throws SQLException {
		DBConnect db = new DBConnect();
		db.con = db.getConnection();
		db.forgot_password_email = new ArrayList<String>();
		if (db.con == null) {
			System.out.println("fail to connected to database");
			System.exit(1);
		}

		try {
			db.prepare_stmts = db.con.prepareStatement("select * from forgot_password_detail;");
			ResultSet rs = db.prepare_stmts.executeQuery();

			while (rs.next()) {

				// System.out.print(rs.getString("user_id"));
				db.forgot_password_email.add(rs.getString("user_id"));
				// System.out.println(" test success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.con.close();
			System.out.println("Disconnecting database");
		}
		return db;
	}

}

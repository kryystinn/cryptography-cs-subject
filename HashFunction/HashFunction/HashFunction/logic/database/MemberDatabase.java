package logic.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDatabase {

	private static Connection conn;

	/**
	 * Connect to a sample database
	 */
	public static void connect() {
		try {
			String url = "jdbc:sqlite:users.db";
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}

	}

	public static Connection getInstance() {
		if (conn == null)
			connect();
		return conn;
	}

	public static void insert(String username, String password) throws SQLException {
		String sql = "INSERT INTO users(user,password) VALUES(?,?)";

			Connection conn = getInstance();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			pstmt.executeUpdate();

	}

	public static boolean select(String username, String password) throws SQLException {
		boolean isInDataBase = false;
		String sql = "SELECT count(*) FROM users "
				+ "WHERE user = ? AND password = ?";

		Connection conn = getInstance();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			if (rs.getInt(1) > 0)
				isInDataBase = true;
		}

		return isInDataBase;
	}

}

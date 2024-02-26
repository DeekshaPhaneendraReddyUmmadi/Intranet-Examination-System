package in.ac.ksrmce.config.questions_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfigQuestions {
	public static Connection getConnection() throws  ClassNotFoundException{
		String url = "jdbc:mysql://localhost:3306/college_project";
		String username = "phanee";
		String password = "Mynewsql 1";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}

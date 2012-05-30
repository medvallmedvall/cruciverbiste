package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql {
	//private static final String url = "jdbc:mysql://pma.olympe-network.com/174463_cruci";
	private static final String url = "jdbc:mysql://localhost/174463_cruci";
	//private static final String url = "jdbc:mysql://localhost/cruciTest";
	//private static final String login = "root";
	//private static final String password = "";
	private static final String login = "root";
	private static final String password = "";
	/*private static final String url = "jdbc:mysql://db405741496.db.1and1.com/db405741496";
	private static final String login = "dbo405741496";
	private static final String password = "cruciverbisme";*/
	private static Connection connection;
	
	private ConnectionMySql() {

	}
	
	public static Connection getInstance() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, login, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}

}

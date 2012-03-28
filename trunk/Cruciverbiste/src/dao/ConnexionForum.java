package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionForum {
	
		private static final String url = "jdbc:mysql://localhost/forum";
		private static final String login = "root";
		private static final String password = "";
		private static Connection forumConnexion;
		
		private ConnexionForum() {
			// TODO Auto-generated constructor stub
		}
		
		public static Connection getInstance() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (forumConnexion == null) {
				try {
					forumConnexion = DriverManager.getConnection(url, login, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return forumConnexion;
		}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Dao<T> {
	protected Connection connection;
	
	public Dao() {
		if (connection == null) {
			connection = ConnectionMySql.getInstance();
		}
	}
	
	public abstract T findById(int id) throws SQLException;
	public abstract T create(T obj) throws SQLException;
	public abstract T update(T obj) throws SQLException;
	public abstract void delete(T obj) throws SQLException;
	
	/*Temp*/
	public void clearTable() throws SQLException {
		String tableName = this.getClass().getSimpleName().replace("Dao", "");
		//System.out.println(tableName);
		String query = "DELETE FROM " + tableName;
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.executeUpdate();
	}

}

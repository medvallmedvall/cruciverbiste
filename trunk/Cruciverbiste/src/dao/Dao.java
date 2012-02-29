package dao;

import java.sql.Connection;

public abstract class Dao<T> {
	protected Connection connection;
	
	public Dao() {
		if (connection == null) {
			connection = ConnectionMySql.getInstance();
		}
	}
	
	public abstract T findById(int id);
	public abstract T create(T obj);
	public abstract T update(T obj);
	public abstract void delete(T obj);

}

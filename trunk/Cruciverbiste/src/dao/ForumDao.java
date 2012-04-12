package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import entities.Sujet;

public class ForumDao {
	protected static Connection forum;
	
	public ForumDao() {
		if (forum == null) {
			forum = ConnexionForum.getInstance();
		}
	}
	
	public List<Sujet> getSujetsRecents() throws SQLException {
		List<Sujet> sujets = new LinkedList<Sujet>();
		String query =  "SELECT topic_id, forum_id, topic_title, topic_fist_poster_name " +
				"FROM phpbb_topics " +
				"ORDER BY topic_time " +
				"LIMIT 0, 5:";
		PreparedStatement pstmt = forum.prepareStatement(query);
		ResultSet results = pstmt.executeQuery();
		while(results.next()) {
			Sujet s = new Sujet(results.getInt("topic_id"), results.getInt("forum_id"), results.getString("topic_title"), results.getString("topic_fist_poster_name"));
			sujets.add(s);
		}
		return sujets;
	}	
}

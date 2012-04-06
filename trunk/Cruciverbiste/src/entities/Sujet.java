package entities;

public class Sujet {
	private int topic_id;
	private int forum_id;
	private String topic_title;
	private String topic_first_poster_name;
	
	public Sujet() {
		
	}
	
	public Sujet(int topic_id, int forum_id, String topic_title, String topic_first_poster_name) {
		this.topic_id = topic_id;
		this.forum_id = forum_id;
		this.topic_title = topic_title;
		this.topic_first_poster_name = topic_first_poster_name;
	}

	public int getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}

	public int getForum_id() {
		return forum_id;
	}

	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}

	public String getTopic_title() {
		return topic_title;
	}

	public void setTopic_title(String topic_title) {
		this.topic_title = topic_title;
	}

	public String getTopic_first_poster_name() {
		return topic_first_poster_name;
	}

	public void setTopic_first_poster_name(String topic_first_poster_name) {
		this.topic_first_poster_name = topic_first_poster_name;
	}
}

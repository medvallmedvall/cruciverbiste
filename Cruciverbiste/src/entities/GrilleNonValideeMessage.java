package entities;

import java.util.Date;

public class GrilleNonValideeMessage {
	private int idMessage;
	private int idGrille;
	private String message;
	private Date date;
	
	public GrilleNonValideeMessage(int idMessage, int idGrille, String message,
			Date date) {
		super();
		this.idMessage = idMessage;
		this.idGrille = idGrille;
		this.message = message;
		this.date = date;
	}
	
	public int getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	public int getIdGrille() {
		return idGrille;
	}
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

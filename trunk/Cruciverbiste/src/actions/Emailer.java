package actions;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.opensymphony.xwork2.ActionSupport;

import dao.UtilisateurDao;

import entities.Utilisateur;

public class Emailer extends ActionSupport {

	private String from;
	private String password;
	private String mail;
	private String pseudo;
	private Utilisateur user;


	static Properties properties = new Properties();
	static
	{
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
	}

	/**
	 * Envoi d'un e-mail par l'administrateur à un utilisateur 
	 * ayant oublié ses paramètres de connexion
	 */
	public String execute() {
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		try {
			user = utilisateurDao.getBy(getMail());
		} catch (SQLException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		if(user == null)
		{
			return ERROR;
		}
		Session session = Session.getDefaultInstance(properties,  
				new javax.mail.Authenticator() {
			protected PasswordAuthentication 
			getPasswordAuthentication() {
				return new 
						PasswordAuthentication("cruciverbiste76@gmail.com", "mrpatrou");
			}});
		String nameUser = user.getNom() + " " + user.getPrenom();
		//Le message Ã  envoyer Ã  l'utilisateur
		String body = "Votre mot de passe est " + "<h3> <b>" + user.getPassword() + " </b></h3><br>" +
				"Vous pouvez revenir sur notre site en cliquant <a href = \"http://localhost:8080/Cruciverbiste/index.jsp\"> ici </a> " + "<br>" +
				" Le site du cruciverbiste ";

		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("cruciverbiste76@gmail.com", "Le site du Cruciverbiste"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(getMail(), nameUser));
			message.setSubject("Votre mot de passe");
		} catch (UnsupportedEncodingException e) {
			addActionError(e.getMessage());
			return ERROR;
		} catch (MessagingException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		MimeMultipart multipart = new MimeMultipart("related");
		//Le contenu du messgae
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			messageBodyPart.setContent(body, "text/html");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	public  String getPseudo() {
		return pseudo;
	}

	public void setpseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		Emailer.properties = properties;
	}
}
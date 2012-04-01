package dao;

import entities.Commentaire;
import entities.Mot;
import entities.Theme;
import entities.Utilisateur;

public class DaoFactory {
	
	public static Dao<Theme> getThemeDao() {
		return new ThemeDao();
	}

	public static Dao<Mot> getMotDao() {
		return new MotDao();
	}

	public static Dao<Commentaire> getCommentaireDao() {
		return new CommentaireDao();
	}

	public static Dao<Utilisateur> getUtilisateurDao() {
		return new UtilisateurDao();
	}
}

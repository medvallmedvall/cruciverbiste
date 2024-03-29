package actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;
import dao.LangueDao;
import dao.LettreGrilleCreationDao;
import dao.ThemeDao;

import entities.Grille;
import entities.Langue;
import entities.LettreGrilleCreation;
import entities.Theme;
import entities.Utilisateur;

public class CreateGridAction extends ActionSupport {
	private final int MIN_ROW = 2;
	private final int MIN_COL = 2;
	private final int MAX_ROW = 20;
	private final int MAX_COL = 15;
	private int idGrille;
	private int idTypeGrille;
	private Grille grille;
	private boolean creerGrille;
	private List<Langue> langues;
	private List<Theme> themes;
	List<LettreGrilleCreation> lettresGrille;
	
	
	/**
	 * Creation de grille
	 */
	public CreateGridAction() {
		idGrille = -1;
		idTypeGrille = -1;
		creerGrille = false;
		LangueDao langueDao = new LangueDao();
		try {
			setLangues(langueDao.getLangues());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ThemeDao themeDao = new ThemeDao();
		try {
			setThemes(themeDao.getThemes());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String auth = null;
		if (session.containsKey("authentification")) {
			auth = (String) session.get("authentification");
		}
		if ((auth == null) || (!auth.equals("true"))) {
			addActionError(getText("message.autorisation"));
			return ERROR;
		}
		int idUser = (Integer) session.get("idUser");
		if (creerGrille) {
			//ajout de la nouvelle grille dans la base pour pouvoir l'editer
			if (grille.getNomGrille().equals("")) {
				addActionError(getText("message.grillevide"));
			}
			if ((grille.getHauteur() < MIN_ROW) || (grille.getHauteur() > MAX_ROW)) {
				addActionError(getText("message.grilletailleligne"));
			}
			if ((grille.getLargeur() < MIN_COL) || (grille.getLargeur() > MAX_COL)) {
				addActionError(getText("message.grilletaillecolonne"));
			}
			if (hasActionErrors()) {
				return INPUT;
			}
			grille.setIdTypeGrille(idTypeGrille);
			Utilisateur u = new Utilisateur();
			u.setIdUtilisateur(idUser);
			grille.setUtilisateur(u);
			GrilleDao dao = new GrilleDao();
			if (((session.containsKey("droit")) &&
					((Integer) session.get("droit") >= 1))) {
				try {
					grille = dao.createGrilleConcours(grille);
				} catch (SQLException e) {
					addActionError(e.getMessage());
					e.printStackTrace();
					return ERROR;
				}
			}
			try {
				grille = dao.create(grille);
			} catch (SQLException e) {
				e.printStackTrace();
				addActionError(e.getMessage());
				return ERROR;
			}
			
		}
		else if ((idTypeGrille != -1) && (idGrille == -1)) {
			//creation d'une nouvelle grille vide
			grille = new Grille();
			grille.setIdGrille(-1);
		}
		else if (idGrille != -1) {
			//on charge une grille
			GrilleDao dao = new GrilleDao();
			try {
				grille = dao.findById(idGrille);
				if (grille == null) {
					//addActionError("La grille n'existe pas");
					addActionError(getText("message.grilleinexistante"));
					return ERROR;
				}
				LettreGrilleCreationDao lettreDao = 
						new LettreGrilleCreationDao();
				lettresGrille = lettreDao.getByIdGrille(idGrille);
			} catch (SQLException e) {
				e.printStackTrace();
				addActionError(e.getMessage());
				return ERROR;
			}
		}
		else {
			//addActionError("Page invalide");
			addActionError(getText("message.pageinv"));
			return ERROR;
		}
		return SUCCESS;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public List<Langue> getLangues() {
		return langues;
	}

	public void setLangues(List<Langue> langues) {
		this.langues = langues;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public int getIdTypeGrille() {
		return idTypeGrille;
	}

	public void setIdTypeGrille(int idTypeGrille) {
		this.idTypeGrille = idTypeGrille;
	}
	
	public boolean isCreerGrille() {
		return creerGrille;
	}

	public void setCreerGrille(boolean creerGrille) {
		this.creerGrille = creerGrille;
	}

	public List<LettreGrilleCreation> getLettresGrille() {
		return lettresGrille;
	}

	public void setLettresGrille(List<LettreGrilleCreation> lettresGrille) {
		this.lettresGrille = lettresGrille;
	}
	
	
}

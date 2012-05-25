package actions;


import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleEnCoursUtilisateurDao;

import entities.LettreEnCour;

public class Sauvegarder extends ActionSupport{

	private int idGrille;
	private String listeLettre;
	private boolean sauvegardeOK;
	private int idGrilleEnCours;


	public Sauvegarder() {
		sauvegardeOK = false;
	}

	
	/**
	 * Sauvegarder une grille en cours de jeu
	 */
	@Override
	public String execute() throws Exception{
		if(listeLettre==null){
			//addActionError("La liste de lettre est nulle");
			addActionError(getText("message.listelettre"));
			return ERROR;
		}
		if(listeLettre.equals("")){
			addActionError("");
			return ERROR;
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object auth = session.get("authentification");
		if ((auth != null) && (((String)auth).equals("true"))){
			int idUser = (Integer)session.get("idUser");
			String[] tab=listeLettre.split("/");
			List<LettreEnCour> list=new LinkedList<LettreEnCour>();
			for (int i = 0; i < tab.length; i++) {
				String[] tabListe=tab[i].split(":");
				String lettre=tabListe[0];
				int coordX=Integer.parseInt(tabListe[1]);
				int coordY=Integer.parseInt(tabListe[2]);
				LettreEnCour lettreEnCour=new LettreEnCour(lettre, coordX, coordY);
				list.add(lettreEnCour);
			}
			GrilleEnCoursUtilisateurDao dao=new GrilleEnCoursUtilisateurDao();
			try {
					sauvegardeOK = dao.Sauvegarder(idGrille, idUser, list);
			} catch (SQLException e) {
				addActionError(e.getMessage());
				e.printStackTrace();
				return ERROR;
			}
		}// cookies
		return SUCCESS;
	}

	public String getListeLettre() {
		return listeLettre;
	}

	public void setListeLettre(String listeLettre) {
		this.listeLettre = listeLettre;
	}

	public boolean isSauvegardeOK() {
		return sauvegardeOK;
	}

	public void setSauvegardeOK(boolean sauvegardeOK) {
		this.sauvegardeOK = sauvegardeOK;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public int getIdGrilleEnCours() {
		return idGrilleEnCours;
	}

	public void setIdGrilleEnCours(int idGrilleEnCours) {
		this.idGrilleEnCours = idGrilleEnCours;
	}
}
package actions;


import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.ws.http.HTTPBinding;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.DefaultTextProvider;

import dao.GrilleDao;
import dao.GrilleEnCoursUtilisateurDao;
import dao.LettreEnCoursDao;

import entities.Grille;
import entities.GrilleEnCoursUtilisateur;
import entities.LettreEnCour;

@SuppressWarnings("serial")
public class PlayAction extends ActionSupport {
	private int idGrille;
	//private Grilles grille;
	private Grille grille;
	
	public PlayAction() {
		super();
		idGrille = -1;
	}
	
	
	/**
	 * La phase de jeu , remplissage des grilles
	 */
	@Override
	public String execute() {
		//selectionner la grille avec l'id idGrid
				GrilleDao dao = new GrilleDao();
				String listLettreEnCour=null;
				try {
					grille = dao.findById(idGrille);
					Map<String, Object> session = ActionContext.getContext().getSession();
					
					if(session.containsKey("idUser")){
						GrilleEnCoursUtilisateurDao grilleEnCoursUtilisateurDao=new GrilleEnCoursUtilisateurDao();
						GrilleEnCoursUtilisateur grilleEnCoursUtilisateur=new GrilleEnCoursUtilisateur();
			
						grilleEnCoursUtilisateur=grilleEnCoursUtilisateurDao.findByGrilleUser(grille.getIdGrille(), (Integer) session.get("idUser"));
						
						LettreEnCoursDao lettreEnCoursDao=new LettreEnCoursDao();
			
						if(grilleEnCoursUtilisateur!=null){
							listLettreEnCour=lettreEnCoursDao.findByid(grille.getIdGrille(), grilleEnCoursUtilisateur.getIdGrilleEnCours());
							if(listLettreEnCour!=null){
								System.out.println(listLettreEnCour);
								session.put("listLettre", listLettreEnCour);
							}else{
								if(session.containsKey("listLettre")){
									session.put("listLettre", "");
								}else
									session.put("listLettre", "");	
							}
						}else{
							if(session.containsKey("listLettre")){
								session.put("listLettre", "");
							}else
								session.put("listLettre", "");
						}
					}else{
						if(session.containsKey("listLettre")){
							session.put("listLettre", "");
						}else
							session.put("listLettre", "");
					}
				} catch (SQLException e) {
					addActionError(e.getMessage());
					return ERROR;
				}
				//si la grille n'existe pas on retourne une erreur
				if (grille == null) {
					addActionError("La grille n° " + idGrille + " n'existe pas");
					return ERROR;
				}
				if (!grille.isEstValidee()) {
					addActionError("La grille n° " + idGrille + " n'existe pas");
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
	
}

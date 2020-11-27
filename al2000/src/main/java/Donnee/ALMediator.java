package main.java.Donnee;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.BDD.Requete;
import main.java.BDD.TransactionBanque;
import main.java.Cinema.Film;
import main.java.Client.Abonne;
import main.java.Client.NonAbonne;
import main.java.Client.Utilisateur;
import main.java.Controller.Controleur;
import main.java.Vues.VueAccueil;

public class ALMediator {

	private Statistiques outils_stat;
	private Utilisateur user;
	private Controleur controleur;
	private VueAccueil acceuil;
	private Requete requete;
	private TransactionBanque t_banque;

	public ALMediator() {
		this.acceuil = new VueAccueil();
		this.outils_stat = new Statistiques();
		this.controleur = new Controleur();
		this.requete = new Requete();
		this.t_banque = new TransactionBanque();
		// on attendras l'insertion d'une carte pour instancier User.
	}

	public void IdentificationAcceuil(int debug) throws NumberFormatException, SQLException {
		// verifie le lecteur carte pour idientifier le client, String debug pour
		// contourner l'abscnece de carte)
		if (this.controleur.haveCarteAbo()) {
			ArrayList<Abonne> res = requete.getAbo(Integer.parseInt(this.controleur.lireCarteAbo()));
			this.user = res!=null ? res.get(0) : null;
			
		//else if (debug != -1) {
			
		//}
			
		} 
		else if (this.controleur.haveCarteBleu()) {
			ArrayList<NonAbonne> res = requete.getNabo(Integer.parseInt(this.controleur.lireCarteBleu()));
			this.user = res!=null ? res.get(0) : null;
		}
	}

	/**
	 * Film f = le films a louer renvoie int suivant dtatu execution : 1 = solde
	 * insuffisant 2 = autres
	 * 
	 */
	public int louerFilms(Film f) {
		// verification possibilit� d'emprunt
		if (user.autorise_nb_loc() && user.solde_suffisant_loc()) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * pr�condition : l'udtilisateur est identifier depuis l'acceuil, le dvd est
	 * ins�r�
	 */
	public void rendreUnFilm() {
		// lecture du dvd :
		if (this.controleur.haveDVD()) {
			String id = this.controleur.lireDVD();
			// verification avec emprunt de l'utilisateur
			// TODO: gestion du dvd ejecter si mauvais , stocker sinon, update
		}
	}
	
	public ArrayList<Film> RechercheParTitre(String titre){
		String sql = "Select * From LesFilms where titre LIKE '%"+titre+"%'";
		return requete.getFilms(sql);
	}
	
	public ArrayList<Film> RechercheParGenre(String genre){
		String sql = "Select * "
				+ "From LesFilms F, LesGenres g "
				+ " where F.titre= g.titre"
				+ " and g.genre LIKE '%"+genre+"%'";
		return requete.getFilms(sql);
	}
	
	public ArrayList<Film> RechercheParRealisateurs(String rea){
		String sql = "Select *"
				+ " From LesFilms F, LesRealisations R,  "
				+ "where F.titre=R.titre"
				+ "and R.real LIKE '%"+rea+"%'";
		return requete.getFilms(sql);
	}
	
	public ArrayList<Film> RechercheParActeur(String acteur){
		String sql = "Select * "
				+ "From LesFilms F, LesParticipations P "
				+ "where F.titre=P.titre"
				+ "and P.acteur LIKE '%"+acteur+"%'";
		return requete.getFilms(sql);
	}

}
